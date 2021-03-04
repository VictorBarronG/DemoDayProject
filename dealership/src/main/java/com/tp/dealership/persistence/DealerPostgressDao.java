package com.tp.dealership.persistence;

import com.tp.dealership.controllers.SearchFilterParameters;
import com.tp.dealership.exceptions.InvalidIdException;
import com.tp.dealership.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile({"production","daoTesting"})
public class DealerPostgressDao implements DealerDao{

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Car> getCollection(){
        List<Car> collection = template.query("SELECT a.id, b.make, c.model, miles, color, year, owners, passinspec, vin, price, description, \"imagePath\"\n" +
                "\tFROM public.\"car collection\" a\n" +
                "\tINNER JOIN public.\"makes\" b\n" +
                "\tON b.id = a.makeid \n" +
                "\tINNER JOIN public.\"models\" c\n" +
                "\tON c.id = a.modelid;", new CarMapper());

        return collection;
    }

    private Integer addMake(String toAdd){
        Integer makeModelId = template.queryForObject("INSERT INTO public.makes(\n" +
                "\tmake)\n" +
                "\tVALUES (?) RETURNING id;", new MakeModelIdMapper(), toAdd);
        return makeModelId;
    }

    private Integer addOrRetrieveMake(Car toAdd){
        Integer make;
        try {
            make = template.queryForObject("SELECT id \n" +
                    "FROM \"makes\"\n" +
                    "WHERE make = ?", new MakeModelIdMapper(), toAdd.getMake());
        }catch(EmptyResultDataAccessException e){
            make = null;
        }
        if(make == null){
            make = addMake(toAdd.getMake());
        }
        return make;
    }

    private Integer addModel(String toAdd){
        Integer modelId = template.queryForObject("INSERT INTO public.models(\n" +
                "\tmodel)\n" +
                "\tVALUES (?) RETURNING id;", new MakeModelIdMapper(), toAdd);
        return modelId;
    }

    private Integer addOrRetrieveModel(Car toAdd){
        Integer model;
        try {
            model = template.queryForObject("SELECT id \n" +
                    "FROM \"models\"\n" +
                    "WHERE model = ?", new MakeModelIdMapper(), toAdd.getModel());
        }catch(EmptyResultDataAccessException e){
            model = null;
        }
        if(model == null){
            model = addModel(toAdd.getModel());
        }
        return model;
    }

    private void existOrAddMakeModel(Integer makeId, Integer modelId){
        Integer makeModel;
        try {
            makeModel = template.queryForObject("SELECT id \n" +
                    "FROM \"makemodels\"\n" +
                    "WHERE makeid = ? AND modelid = ?", new MakeModelIdMapper(), makeId, modelId);
        }catch (EmptyResultDataAccessException e){
            makeModel =  null;
        }
        if(makeModel == null){
             addMakeModel(makeId, modelId);
        }
    }

    private void addMakeModel(Integer makeId, Integer modelId) {
        template.execute("INSERT INTO public.makemodels(\n" +
                "\tmakeid, modelid)\n" +
                "\tVALUES ("+ makeId +", "+ modelId +");");
    }

    @Override
    public Car addCar(Car toAdd){
        //adds make and model association to makemodel table and returns the id so that it can be assigned in the
        //next insert to car collection
        Integer makeId = addOrRetrieveMake(toAdd);
        Integer modelId = addOrRetrieveModel(toAdd);
        existOrAddMakeModel(makeId,modelId);

        //providing the ? for values to then add values into data to avoid sql injection.
        //change query for update
        Integer carId = template.queryForObject("INSERT INTO \"car collection\"(\n" +
                "\tmakeid, modelid, miles, color, year, owners, passinspec, vin, price, description, \"imagePath\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING \"id\";", new CarIdMapper(), makeId, modelId, toAdd.getMiles(), toAdd.getColor(),
                toAdd.getYear(),toAdd.getOwners(),toAdd.isPassedInspec(),toAdd.getVin(), toAdd.getPrice(), toAdd.getDescription(), toAdd.getImagePath());

        toAdd.setId(carId);
        return toAdd;
    }

    @Override
    public Car editCar(Car toAdd) {
        Integer id = template.queryForObject("UPDATE public.\"car collection\"\n" +
                        "\tSET make=?, model=?, miles=?, color=?, year=?, owners=?, passinspec=?, vin=?, price=?\n" +
                        "\tWHERE id= ? RETURNING \"id\";", new CarIdMapper(), toAdd.getMake(), toAdd.getModel(), toAdd.getMiles(), toAdd.getColor(),
                toAdd.getYear(),toAdd.getOwners(),toAdd.isPassedInspec(),toAdd.getVin(), toAdd.getPrice(),toAdd.getId());
        return toAdd;
    }

    @Override
    public void deleteCar(Integer id) {
        template.execute("DELETE FROM public.\"car collection\"\n" +
                "\tWHERE id= "+ id +";");
    }

    @Override
    public Car getById(Integer id) throws InvalidIdException {
        Car toReturn = null;
        try {
            toReturn = template.queryForObject("SELECT id, make, model, miles, color, year, owners, passinspec, vin, price\n" +
                    "\tFROM public.\"car collection\"\n" +
                    "\tWHERE id = ?;", new CarMapper(), id);
        }catch (EmptyResultDataAccessException e){
            throw new InvalidIdException("Invalid Id.");
        }
        return toReturn;
    }

    @Override
    public List<Car> filterSearch(SearchFilterParameters toSearch) {
        List<Car> collection = template.query("SELECT id, make, model, miles, color, year, owners, passinspec, vin, price\n" +
                "\tFROM public.\"car collection\" " + filteredInput(toSearch), new CarMapper());

        return collection;
    }


    //helper method that builds a string for the where clause of the sql for filterSearch
    private String filteredInput(SearchFilterParameters toSearch) {
        String toReturn = "";
        Boolean needsAnd  = true;
        if(toSearch.getYearStart() != null || toSearch.getYearEnd() != null){
            if(toSearch.getYearStart() == null){
                toReturn = toReturn + ("year >= 1992");
            }
            else{
                toReturn = toReturn + ("year >= " + toSearch.getYearStart());
            }
            toReturn += " AND ";
            if(toSearch.getYearEnd() == null){
                toReturn = toReturn + ("year <= 2020");
            }
            else{
                toReturn = toReturn + ("year <= " + toSearch.getYearEnd());
            }
            needsAnd = false;
        } //handles year

        if(toSearch.getPriceStart() != null || toSearch.getPriceEnd() != null){
            if(!needsAnd){
                toReturn += " AND ";
            }
            if(toSearch.getPriceStart() == null){
                toReturn = toReturn + ("price > 0");
            }
            else{
                toReturn = toReturn + ("price >= " + toSearch.getPriceStart());
            }
            toReturn += " AND ";
            if(toSearch.getPriceEnd() == null){
                toReturn = toReturn + ("price <= 1000000");
            }
            else{
                toReturn = toReturn + ("price <= " + toSearch.getPriceEnd());
            }
            needsAnd = false;
        }//price range

        if(toSearch.getMake() != null){
            if(!needsAnd){
                toReturn += " AND ";
            }
            toReturn = toReturn + ("make = '" + toSearch.getMake() + "'");
            needsAnd = false;
        } //handles make (S)

        if(toSearch.getModel() != null){
            if(!needsAnd){
                toReturn +=" AND ";
            }
            toReturn = toReturn + ("model = '" + toSearch.getModel() + "'");
            needsAnd = false;
        }//handles model (S)

        if(toSearch.getMiles() != null){
            if(!needsAnd){
                toReturn +=" AND ";
            }
            toReturn = toReturn + ("miles < " + toSearch.getMiles());
            needsAnd = false;
        }//handles miles

        if(toSearch.getColor() != null){
            if(!needsAnd){
                toReturn += " AND ";
            }
            toReturn = toReturn + ("color = '" + toSearch.getColor() +"'");
            needsAnd = false;
        }//handles color (S)

        if(toSearch.getOwners() != null){
            if(!needsAnd){
                toReturn += " AND ";
            }
            toReturn = toReturn + ("owners <= " + toSearch.getOwners());
            needsAnd = false;
        }//handles owners

        if(toSearch.getPassinspec() != null){
            if(!needsAnd){
                toReturn += " AND ";
            }
            toReturn = toReturn + ("passinspec = " + toSearch.getPassinspec());
            needsAnd = false;
        }//handles passinspec

        //only inserts where and and semicolon if any parameters are available
        if(!toReturn.isEmpty()){
            toReturn = "WHERE " + toReturn + ";";
        }

        return toReturn;
    }


    class CarMapper implements RowMapper<Car>{

        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car mappedCar = new Car();
            mappedCar.setId(resultSet.getInt("id"));
            mappedCar.setMake(resultSet.getString("make") );
            mappedCar.setModel(resultSet.getString("model"));
            mappedCar.setColor(resultSet.getString("color"));
            mappedCar.setYear(resultSet.getInt("year"));
            mappedCar.setOwners(resultSet.getInt("owners"));
            mappedCar.setMiles(resultSet.getInt("miles"));
            mappedCar.setPassedInspec(resultSet.getBoolean("passinspec"));
            mappedCar.setVin(resultSet.getString("vin"));
            mappedCar.setPrice(resultSet.getInt("price"));

            return mappedCar;
        }

    }

    class CarIdMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }
    }

    class MakeModelIdMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }
    }


}

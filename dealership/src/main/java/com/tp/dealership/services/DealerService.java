package com.tp.dealership.services;

import com.tp.dealership.controllers.SearchfilterParameters;
import com.tp.dealership.exceptions.InvalidIdException;
import com.tp.dealership.exceptions.InvalidInputException;
import com.tp.dealership.models.Car;
import com.tp.dealership.persistence.DealerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class DealerService {
    @Autowired
    DealerDao dao;

    public List<Car> getCollection(){ return dao.getCollection();}

    //checks for
    //throws invalid for isBlank on Make,model,price,vin
    //throws invalid for range for miles,price, owners
    //changes all string inputs to lowercase
    public Car addCar(Car toAdd) throws InvalidInputException{
        if(!(toAdd.getYear() > 1950 && toAdd.getYear() < 2020)){throw new InvalidInputException("Year is not in range.");}
        if(toAdd.getMake().isBlank()){ throw new InvalidInputException("Make is empty."); }
        toAdd.setMake(toAdd.getMake().toLowerCase());
        if(toAdd.getModel().isBlank()){ throw new InvalidInputException("Model is empty."); }
        toAdd.setModel(toAdd.getModel().toLowerCase());
        if(toAdd.getColor().isBlank()){ throw new InvalidInputException("Color is empty.");}
        toAdd.setColor(toAdd.getColor().toLowerCase());
        if(toAdd.getPrice() < 1000){throw new InvalidInputException("Price is too low.");}
        if(toAdd.getVin().isBlank()){throw new InvalidInputException("Vin is empty.");}
        toAdd.setVin(toAdd.getVin().toLowerCase());
        if(!(toAdd.getMiles() > 10 && toAdd.getMiles() < 200000) ){throw new InvalidInputException("Miles are not within range."); }
        if(toAdd.getOwners() < 1){throw new InvalidInputException("Owners is incorrect");}
        return dao.addCar(toAdd);
    }

    public Car editCar(Car toAdd) throws InvalidInputException {
        if(!(toAdd.getYear() > 1950 && toAdd.getYear() < 2020)){throw new InvalidInputException("Year is not in range.");}
        if(toAdd.getMake().isBlank()){ throw new InvalidInputException("Make is empty."); }
        toAdd.setMake(toAdd.getMake().toLowerCase());
        if(toAdd.getModel().isBlank()){ throw new InvalidInputException("Model is empty."); }
        toAdd.setModel(toAdd.getModel().toLowerCase());
        if(toAdd.getColor().isBlank()){ throw new InvalidInputException("Color is empty.");}
        toAdd.setColor(toAdd.getColor().toLowerCase());
        if(toAdd.getPrice() < 1000){throw new InvalidInputException("Price is too low.");}
        if(toAdd.getVin().isBlank()){throw new InvalidInputException("Vin is empty.");}
        toAdd.setVin(toAdd.getVin().toLowerCase());
        if(!(toAdd.getMiles() > 10 && toAdd.getMiles() < 200000) ){throw new InvalidInputException("Miles are not within range."); }
        if(toAdd.getOwners() < 1){throw new InvalidInputException("Owners is incorrect");}
        return dao.editCar(toAdd);
    }

// this one is okay if number is not in range or nonexistent
    public void deleteCar(Integer id) {
        //check to see if it exists?
        dao.deleteCar(id);

    }

    public Car getById(Integer id) throws InvalidIdException {
        return dao.getById(id);
    }

    //VALIDATE ALL ENTRIES ARE WITHIN RANGE AND NOT EMPTY AND CAST STRINGS TO LOWERCASE
    //dont have to worry about Strings being empty because if its null it wont search that specific one and this will have a drop down on user side
    public List<Car> filterSearch(SearchfilterParameters toSearch) throws InvalidInputException {
        if(toSearch.getYearStart() != null){
            if(!(toSearch.getYearStart() > 1950 && toSearch.getYearStart() < 2020)){throw new InvalidInputException("Start year needs to be in range of 1992 to 2020.");}
        }
        if(toSearch.getYearEnd() != null){
            if(!(toSearch.getYearStart() > 1950 && toSearch.getYearStart() < 2020)){throw new InvalidInputException("End year needs to be in range of 1992 to 2020");}
        }
        if(toSearch.getYearStart() != null || toSearch.getYearEnd() != null){
            if(toSearch.getYearStart() > toSearch.getYearEnd()){throw new InvalidInputException("Year range is invalid.");}
        }
        if(toSearch.getMake() != null){
            toSearch.setMake(toSearch.getMake().toLowerCase());
        }
        if(toSearch.getModel() != null){
            toSearch.setModel(toSearch.getModel().toLowerCase());
        }
        if(toSearch.getColor() != null){
            toSearch.setColor(toSearch.getColor().toLowerCase());
        }
        if(toSearch.getPriceStart() != null){
            if(toSearch.getPriceStart() < 0){throw new InvalidInputException("Price is not valid entry.");}
        }
        if(toSearch.getPriceEnd() != null){
            if(toSearch.getPriceEnd() < 0){throw new InvalidInputException("Price is not valid entry.");}
        }
        if(toSearch.getPriceEnd() != null || toSearch.getPriceStart() != null){
            if(toSearch.getPriceEnd() < toSearch.getPriceStart()){throw new InvalidInputException("Price range is invalid.");}
        }

        if(toSearch.getMiles() != null){
            if(!(toSearch.getMiles() > 10 && toSearch.getMiles() < 200000) ){throw new InvalidInputException("Miles are not within range."); }
        }
        if(toSearch.getOwners() != null){
            if(toSearch.getOwners() < 1){throw new InvalidInputException("Owners is incorrect");}
        }
        return dao.filterSearch(toSearch);
    }
}

package com.tp.dealership.persistence;


import com.tp.dealership.controllers.SearchFilterParameters;
import com.tp.dealership.exceptions.InvalidIdException;
import com.tp.dealership.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daoTesting")
public class DealerPostgresDaoTest {

    @Autowired
    DealerPostgressDao toTest;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup(){
        //
        template.update("TRUNCATE \"car collection\" RESTART IDENTITY; ");//the table with double quotes just do it from pgadmin

        //only have one table so no need to update or clear any other table but this one
    }


    @Test
    public void getById() throws InvalidIdException {
        Car toAdd = new Car();
        toAdd.setMake("Honda");
        toAdd.setModel("NSX");
        toAdd.setYear(2002);
        toAdd.setMiles(10000);
        toAdd.setColor("white");
        toAdd.setOwners(1);
        toAdd.setPassedInspec(true);
        toAdd.setVin("UNIQUE");
        toAdd.setPrice(40000.00);


        Car returned = toTest.addCar(toAdd);
        assertEquals(1, toTest.getById(1).getId());
    }



    @Test
    public void addCarTest() throws InvalidIdException {
        Car toAdd = new Car();
        toAdd.setMake("Honda");
        toAdd.setModel("NSX");
        toAdd.setYear(2002);
        toAdd.setMiles(10000);
        toAdd.setColor("white");
        toAdd.setOwners(1);
        toAdd.setPassedInspec(true);
        toAdd.setVin("UNIQUE");
        toAdd.setPrice(40000.00);


        Car returned = toTest.addCar(toAdd);
        int id = returned.getId();
        assertEquals(1, toTest.getById(id).getId());
        assertEquals("Honda", toTest.getById(id).getMake());
        assertEquals("NSX", toTest.getById(id).getModel());
        assertEquals(2002, toTest.getById(id).getYear());
        assertEquals(10000, toTest.getById(id).getMiles());
        assertEquals("white", toTest.getById(id).getColor());
        assertEquals(1, toTest.getById(id).getOwners());
        assertEquals(true, toTest.getById(id).isPassedInspec());
        assertEquals("UNIQUE", toTest.getById(id).getVin());
        assertEquals(40000.00, toTest.getById(id).getPrice());

    }


    @Test
    public void editCarTest() throws InvalidIdException {
        Car original = new Car();
        original.setMake("Honda");
        original.setModel("NSX");
        original.setYear(2002);
        original.setMiles(10000);
        original.setColor("white");
        original.setOwners(1);
        original.setPassedInspec(true);
        original.setVin("UNIQUE");
        original.setPrice(40000.00);


        Car editedCar = new Car();
        editedCar.setId(1);
        editedCar.setMake("Toyota");
        editedCar.setModel("Corolla");
        editedCar.setYear(2008);
        editedCar.setMiles(50000);
        editedCar.setColor("blue");
        editedCar.setOwners(2);
        editedCar.setPassedInspec(true);
        editedCar.setVin("MadeUpVin");
        editedCar.setPrice(8000.00);


        Car returned = toTest.addCar(original);
        int id = returned.getId();
        Car toCheck = toTest.editCar(editedCar);
        assertEquals(1, toTest.getById(id).getId());
        assertEquals("Toyota", toTest.getById(id).getMake());
        assertEquals("Corolla", toTest.getById(id).getModel());
        assertEquals(2008, toTest.getById(id).getYear());
        assertEquals(50000, toTest.getById(id).getMiles());
        assertEquals("blue", toTest.getById(id).getColor());
        assertEquals(2, toTest.getById(id).getOwners());
        assertEquals(true, toTest.getById(id).isPassedInspec());
        assertEquals("MadeUpVin", toTest.getById(id).getVin());
        assertEquals(8000.00, toTest.getById(id).getPrice());
    }


    //test delete
    //send car id that doesnt exist

    @Test
    public void deleteCarTest(){
        Car original = new Car();
        original.setMake("Honda");
        original.setModel("NSX");
        original.setYear(2002);
        original.setMiles(10000);
        original.setColor("white");
        original.setOwners(1);
        original.setPassedInspec(true);
        original.setVin("UNIQUE");
        original.setPrice(40000.00);

        Car returned = toTest.addCar(original);

        toTest.deleteCar(1);
        int id = returned.getId();
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getId());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getMake());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getModel());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getYear());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getMiles());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getColor());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getOwners());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).isPassedInspec());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getVin());
        assertThrows(InvalidIdException.class,() -> toTest.getById(id).getPrice());

    }

    @Test
    public void filterSearchTest() throws InvalidIdException {
        Car CarOne = new Car();
        CarOne.setMake("Honda");
        CarOne.setModel("NSX");
        CarOne.setYear(2002);
        CarOne.setMiles(10000);
        CarOne.setColor("white");
        CarOne.setOwners(1);
        CarOne.setPassedInspec(true);
        CarOne.setVin("UNIQUE");
        CarOne.setPrice(40000.00);

        Car CarTwo = new Car();
        CarTwo.setMake("Honda");
        CarTwo.setModel("CRV");
        CarTwo.setYear(2008);
        CarTwo.setMiles(50000);
        CarTwo.setColor("grey");
        CarTwo.setOwners(2);
        CarTwo.setPassedInspec(true);
        CarTwo.setVin("HondaCRv");
        CarTwo.setPrice(6000.00);

        Car carThree = new Car();
        carThree.setMake("Toyota");
        carThree.setModel("Supra");
        carThree.setYear(2019);
        carThree.setMiles(5000);
        carThree.setColor("white");
        carThree.setOwners(1);
        carThree.setPassedInspec(true);
        carThree.setVin("DreamsComeTrue");
        carThree.setPrice(30000.00);

        Car reOne = toTest.addCar(CarOne);
        Car reTwo = toTest.addCar(CarTwo);
        Car reThree = toTest.addCar(carThree);

        SearchFilterParameters toSearch = new SearchFilterParameters();
        toSearch.setMake("Honda");

        List<Car> result = toTest.filterSearch(toSearch);
        //testing single field search
        assertEquals(1, toTest.getById(result.get(0).getId()).getId());
        assertEquals("Honda", toTest.getById(result.get(0).getId()).getMake());
        assertEquals("NSX", toTest.getById(result.get(0).getId()).getModel());
        assertEquals(2002, toTest.getById(result.get(0).getId()).getYear());
        assertEquals(10000, toTest.getById(result.get(0).getId()).getMiles());
        assertEquals("white", toTest.getById(result.get(0).getId()).getColor());
        assertEquals(1, toTest.getById(result.get(0).getId()).getOwners());
        assertEquals(true, toTest.getById(result.get(0).getId()).isPassedInspec());
        assertEquals("UNIQUE", toTest.getById(result.get(0).getId()).getVin());
        assertEquals(40000.00, toTest.getById(result.get(0).getId()).getPrice());


        toSearch.setModel("CRV");

        result = toTest.filterSearch(toSearch);
        //testing search with 2 fields

        assertEquals(2, toTest.getById(result.get(0).getId()).getId());
        assertEquals("Honda", toTest.getById(result.get(0).getId()).getMake());
        assertEquals("CRV", toTest.getById(result.get(0).getId()).getModel());
        assertEquals(2008, toTest.getById(result.get(0).getId()).getYear());
        assertEquals(50000, toTest.getById(result.get(0).getId()).getMiles());
        assertEquals("grey", toTest.getById(result.get(0).getId()).getColor());
        assertEquals(2, toTest.getById(result.get(0).getId()).getOwners());
        assertEquals(true, toTest.getById(result.get(0).getId()).isPassedInspec());
        assertEquals("HondaCRv", toTest.getById(result.get(0).getId()).getVin());
        assertEquals(6000.00, toTest.getById(result.get(0).getId()).getPrice());



    }


}

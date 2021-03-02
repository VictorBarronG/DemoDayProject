package com.tp.dealership.Services;

import com.tp.dealership.exceptions.InvalidInputException;
import com.tp.dealership.models.Car;
import com.tp.dealership.services.DealerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.*;

@CrossOrigin
@SpringBootTest
@ActiveProfiles("serviceTest")
public class DealershipServiceTest {

    @Autowired
    DealerService toTest;




    //this is the golden path
    @Test
    public void addCarServiceTest() throws InvalidInputException {
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
        toAdd.setId(1);

        Car result = toTest.addCar(toAdd);
        int id = result.getId();

        assertEquals(1, result.getId());
        assertEquals("honda", result.getMake());
        assertEquals("nsx", result.getModel());
        assertEquals(2002, result.getYear());
        assertEquals(10000, result.getMiles());
        assertEquals("white", result.getColor());
        assertEquals(1, result.getOwners());
        assertEquals(true, result.isPassedInspec());
        assertEquals("unique", result.getVin());
        assertEquals(40000.00, result.getPrice());

    }

    @Test
    public void addCarServiceInvalidTests() throws InvalidInputException {
        Car toAdd = new Car();
        toAdd.setMake("   ");
        toAdd.setModel("NSX");
        toAdd.setYear(2002);
        toAdd.setMiles(10000);
        toAdd.setColor("white");
        toAdd.setOwners(1);
        toAdd.setPassedInspec(true);
        toAdd.setVin("UNIQUE");
        toAdd.setPrice(40000.00);
        toAdd.setId(1);

        //invalid input for make
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid for model
        toAdd.setMake("Honda");
        toAdd.setModel("   ");
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid year
        toAdd.setModel("NSX");
        toAdd.setYear(1900);
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid miles
        toAdd.setYear(2002);
        toAdd.setMiles(350000);
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid color
        toAdd.setMiles(10000);
        toAdd.setColor("   ");
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid owners
        toAdd.setColor("White");
        toAdd.setOwners(-2);
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid vin
        toAdd.setOwners(1);
        toAdd.setVin("    ");
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));
        //invalid price
        toAdd.setVin("Unique");
        toAdd.setPrice(20);
        assertThrows(InvalidInputException.class,() -> toTest.addCar(toAdd));


    }

    @Test
    public void editCarGoldenTest() throws InvalidInputException {
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
        toAdd.setId(1);

        Car result = toTest.editCar(toAdd);

        assertEquals(1, result.getId());
        assertEquals("honda", result.getMake());
        assertEquals("nsx", result.getModel());
        assertEquals(2002, result.getYear());
        assertEquals(10000, result.getMiles());
        assertEquals("white", result.getColor());
        assertEquals(1, result.getOwners());
        assertEquals(true, result.isPassedInspec());
        assertEquals("unique", result.getVin());
        assertEquals(40000.00, result.getPrice());
    }

    //test editCar Invalids
    @Test
    public void editCarServiceInvalidTests() throws InvalidInputException {
        Car toAdd = new Car();
        toAdd.setMake("   ");
        toAdd.setModel("NSX");
        toAdd.setYear(2002);
        toAdd.setMiles(10000);
        toAdd.setColor("white");
        toAdd.setOwners(1);
        toAdd.setPassedInspec(true);
        toAdd.setVin("UNIQUE");
        toAdd.setPrice(40000.00);
        toAdd.setId(1);

        //invalid input for make
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid for model
        toAdd.setMake("Honda");
        toAdd.setModel("   ");
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid year
        toAdd.setModel("NSX");
        toAdd.setYear(1900);
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid miles
        toAdd.setYear(2002);
        toAdd.setMiles(350000);
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid color
        toAdd.setMiles(10000);
        toAdd.setColor("   ");
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid owners
        toAdd.setColor("White");
        toAdd.setOwners(-2);
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid vin
        toAdd.setOwners(1);
        toAdd.setVin("    ");
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));
        //invalid price
        toAdd.setVin("Unique");
        toAdd.setPrice(20);
        assertThrows(InvalidInputException.class,() -> toTest.editCar(toAdd));


    }

    @Test
    public void filterSearchInvalidTests(){

    }


}

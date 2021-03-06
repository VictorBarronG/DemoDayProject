package com.tp.dealership.persistence;

import com.tp.dealership.controllers.SearchFilterParameters;
import com.tp.dealership.exceptions.InvalidIdException;
import com.tp.dealership.models.Car;

import java.util.List;

public interface DealerDao {
    public List<Car> getCollection();
    public Car addCar(Car toAdd);

    public Car editCar(Car toAdd);
    public void deleteCar(Integer id);

    public Car getById(Integer id) throws InvalidIdException;

    public List<Car> filterSearch(SearchFilterParameters toSearch);

    public List<String> getAllMakes();

    public List<String> getAllModels(String make);
}

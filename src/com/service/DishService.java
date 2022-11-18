package com.service;

import com.dao.DishDao;
import com.domain.Dish;

import java.util.List;

public class DishService {

    DishDao dishDao = new DishDao();

    //get a list of dishes
    public List<Dish> getDishList() throws Exception {
        return dishDao.queryMultiple("select * from dish", Dish.class);
    }

    //get a dish by id
    public Dish getDishById(int id) throws Exception {
        return dishDao.querySingle("select * from dish where id = ?", Dish.class, id);
    }
}

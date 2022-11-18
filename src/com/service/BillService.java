package com.service;

import com.dao.BillDao;

import java.util.UUID;

public class BillService {

    private BillDao billDao = new BillDao();
    private DishService dishService = new DishService();
    private DiningTableService diningTableService = new DiningTableService();

    //1.create a bill
    //2.update the table's state
    //3.if works, return true, otherwise return false
    public boolean orderDish(int dishId, int nums, int diningTable) throws Exception {

        String billId = UUID.randomUUID().toString();

        int update = billDao.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), 'Not paid')", billId, dishId, nums, dishService.getDishById(dishId).getPrice() * nums, diningTable);

        if(update <= 0){
            return false;
        }

        return diningTableService.updateDiningTable(diningTable,"Eating");
    }

}

package com.service;

import com.dao.BillDao;
import com.domain.Bill;

import java.util.List;
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

    //return a list containing all bills
    public List<Bill> displayAllBills() throws Exception {
        return billDao.queryMultiple("select * from bill", Bill.class);
    }

    //to check do we have not paid bill by tableId
    public boolean hasNotPaidBill(int tableId) throws Exception {
        Bill bill = billDao.querySingle("select * from bill where diningTable = ? and state = 'Not paid'", Bill.class, tableId);

        return bill != null;
    }

    public boolean payBill(int diningTable, String method) throws Exception {

        //1.update bill table
        int update = billDao.update("update bill set sate = ? where diningTable = ? and state = 'Not paid'", Bill.class, method, diningTable);
        if(update <= 0){
            return false;
        }

        //2.update diningTable table
        if(!diningTableService.updateTableIntoEmpty(diningTable, "Empty")){
            return false;
        }
        return true;
    }
}

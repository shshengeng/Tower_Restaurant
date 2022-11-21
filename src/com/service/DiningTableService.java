package com.service;
import com.dao.DiningTableDao;
import com.domain.DiningTable;
import java.util.List;


public class DiningTableService {

    private DiningTableDao diningTableDao = new DiningTableDao();

    //get a list of dining table, including id and states
    public List<DiningTable> list() throws Exception {
        return diningTableDao.queryMultiple("select id, state from diningTable", DiningTable.class);
    }

    //get a dining table through id
    public DiningTable getDiningTableById(int id) throws Exception {
        return diningTableDao.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    //to see whether we can reserve table
    public boolean reserveDiningTable(int id, String orderName, String orderTel) throws Exception {
        int affectedRows = diningTableDao.update("update diningTable set state = 'Reserved', orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);

        return affectedRows > 0;
    }

    public boolean updateDiningTable(int id, String state) throws Exception {
        int update = diningTableDao.update("update diningTable set state = ? where id = ?", state, id);

        return update > 0;
    }

    public boolean updateTableIntoEmpty(int tableId, String state) throws Exception {
        int update = diningTableDao.update("update diningTable set state = ?, orderName = ' ', orderTel = ' ' where id = ?", state, tableId);
        return update > 0;
    }

}

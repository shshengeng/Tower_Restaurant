package com.view;

import com.domain.Bill;
import com.domain.DiningTable;
import com.domain.Dish;
import com.domain.Employee;
import com.service.BillService;
import com.service.DiningTableService;
import com.service.DishService;
import com.service.EmployeeService;
import com.utils.Utility;

import java.util.List;

public class TowerRestaurantView {

    private boolean loop = true; //check whether exit menu
    private String key; //key
    private EmployeeService employeeService = new EmployeeService();//
    private DiningTableService diningTableService = new DiningTableService();//
    private DishService dishService = new DishService();
    private BillService billService = new BillService();


    public static void main(String[] args) throws Exception {
        new TowerRestaurantView().mainMenu();
    }


    public void mainMenu() throws Exception {

        while (loop){
            System.out.println("========Tower Restaurant========");
            System.out.println("1 Log in");
            System.out.println("2 Exit");
            System.out.print("Please input your choice: ");
            key = Utility.readString(2);
            switch (key){
                case "1":
                    System.out.print("Please input your Employee ID: ");
                    String id = Utility.readString(50);
                    System.out.print("Please input your password: ");
                    String password = Utility.readString(50);
                    Employee employee= employeeService.getEmployeeByIdAndPwd(id, password);
                    if(employee != null){
                        System.out.println();
                        System.out.println("============Log in successfully============");
                        //second level menu
                        while (loop){
                            System.out.println("=======Welcome "+ employee.getName() + ", here is main page=======");
                            System.out.println("1 Show table's states");
                            System.out.println("2 Reserve tables");
                            System.out.println("3 Display all dishes");
                            System.out.println("4 Order dishes");
                            System.out.println("5 Display bills");
                            System.out.println("6 Bills out");
                            System.out.println("9 Exit main page");
                            System.out.print("Please input your choice: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();
                                    break;
                                case "2":
                                    reserveTable();
                                    break;
                                case "3":
                                    displayAllDishes();
                                    break;
                                case "4":
                                    orderDishes();
                                    break;
                                case "5":
                                    displayAllBills();
                                    break;
                                case "6":
                                    payBills();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("Invalid input, Please try again");
                            }
                        }
                    }else {
                        System.out.println("========Failed to log in========");
                    }
                    break;
                case "2":
                    System.out.println("Thanks for using our system");
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input, Please try again");
            }
        }
    }

    //show table's states
    public void listDiningTable() throws Exception {
        List<DiningTable> list = diningTableService.list();
        System.out.println();
        System.out.println("#DiningTable\t\tDiningTable's states");
        for(DiningTable e : list){
            System.out.println(e);
        }
        System.out.println("========Displays all done========\n");
    }

    //Reserve tables
    public void reserveTable() throws Exception {
        System.out.println("\n==========Reserve Table==========");
        System.out.print("Please input # of table(-1 for exit): ");
        int orderId = Utility.readInt();
        if (orderId == -1){
            System.out.println("==========Cancel Reservation==========\n");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y'){ //Reserve
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if(diningTable == null){
                System.out.println("This table is not exist");
                return;
            }
            if (!(diningTable.getState().equals(" "))){
                System.out.println("This table is reserved");
                return;
            }
            System.out.print("Please input your name: ");
            String name = Utility.readString(32);
            System.out.print("Please input your telephone: ");
            String tel = Utility.readString(32);
            boolean status = diningTableService.reserveDiningTable(orderId, name, tel);
            if(status){
                System.out.println("===You successfully reserve table!===\n");
            }else {
                System.out.println("===Failed to reserve table!===\n");
            }
        }else{
            System.out.println("==========Cancel Reservation==========\n");
        }
    }

    //display all dishes
    public void displayAllDishes() throws Exception {
        System.out.println("\nID \t\t Name \t\t\t\t Type \t\t Price");
        List<Dish> dishList = dishService.getDishList();
        for(Dish e: dishList){
            System.out.println(e);
        }
        System.out.println();
    }

    //order dishes
    public void orderDishes() throws Exception {
        System.out.println("\n========Order dishes========");
        System.out.print("Please input # dining table(-1 for exit): ");
        int orderDiningTableId = Utility.readInt();
        if(orderDiningTableId == -1){
            System.out.println("========Cancel Order========\n");
            return;
        }
        System.out.print("Please input # dish(-1 for exit): ");
        int orderDishId = Utility.readInt();
        if(orderDishId == -1){
            System.out.println("========Cancel Order========\n");
            return;
        }
        System.out.print("Please input amount of dishes(-1 for exit): ");
        int orderNums = Utility.readInt();
        if (orderNums == -1){
            System.out.println("========Cancel Order========\n");
            return;
        }

        //check if this table exist
        DiningTable diningTable = diningTableService.getDiningTableById(orderDiningTableId);
        if(diningTable == null){
            System.out.println("This table is not exist\n");
            return;
        }
        Dish dish = dishService.getDishById(orderDishId);
        if(dish == null){
            System.out.println("This dish is not exist\n");
            return;
        }

        if(billService.orderDish(orderDishId, orderNums, orderDiningTableId)){
            System.out.println("Order dish successfully!\n");
        }else {
            System.out.println("Failed to order dish!\n");
        }
    }

    //display all bills
    public void displayAllBills() throws Exception {
        List<Bill> bills = billService.displayAllBills();
        System.out.println("\nNumber\t\tDishId\t\tDishAmount\t\tMoney\t\tTableId\t\tDate\t\t\t\t\t\t\tStates");
        for(Bill e : bills){
            System.out.println(e);
        }
        System.out.println("=====Displays all bills done=====\n");
    }

    public void payBills() throws Exception {
        System.out.println("\n=====Bills out=====");
        System.out.print("Please input # table(-1 for exit): ");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1){
            System.out.println("======Cancel to check=====\n");
            return;
        }
        //check if this table exist
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if(diningTable == null){
            System.out.println("This table is not exist\n");
            return;
        }
        if(!billService.hasNotPaidBill(diningTableId)){
            System.out.println("This table has no bill to pay\n");
            return;
        }
        System.out.print("Please input method(cash/credit): ");
        String payMethod = Utility.readString(20, "");
        if(payMethod.equals("")){
            System.out.println("======Cancel to check=====\n");
            return;
        }
        char c = Utility.readConfirmSelection();
        if(c == 'Y'){
            if(billService.payBill(diningTableId, payMethod)){
                System.out.println("=====Pay the bill successfully=====\n");
            }else {
                System.out.println("======Failed to pay the bill=====\n");
            }
        }else {
            System.out.println("======Cancel to check=====\n");
        }
        return;


    }
}

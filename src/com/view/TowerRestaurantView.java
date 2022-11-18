package com.view;

import com.domain.DiningTable;
import com.domain.Employee;
import com.service.DiningTableService;
import com.service.EmployeeService;
import com.utils.Utility;

import java.util.List;

public class TowerRestaurantView {

    private boolean loop = true; //check whether exit menu
    private String key; //key
    private EmployeeService employeeService = new EmployeeService();//
    private DiningTableService diningTableService = new DiningTableService();//


    public static void main(String[] args) throws Exception {
        new TowerRestaurantView().mainMenu();
    }


    public void mainMenu() throws Exception {

        while (loop){
            System.out.println("========Tower Restaurant========");
            System.out.println("1 Log in");
            System.out.println("2 Exit");
            System.out.print("Please input your choice: ");
            key = Utility.readString(1);
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
                                    break;
                                case "4":
                                    break;
                                case "5":
                                    break;
                                case "6":
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
}

package com.utils;
import java.util.Locale;
import java.util.Scanner;

/**
 * Deal with different input entered by user
 */
public class Utility {
    //静态属性...
    private static Scanner scanner = new Scanner(System.in);

    /**
     * read input, value range from 1-6
     * @return 1--6
     */
    public static char readMenuSelection(){
        char c;
        for(; ;){
            String str = readKeyBoard(1,false);
            c = str.charAt(0);
            if(c != '1' && c != '2' &&
                    c != '3' && c != '4' && c !=  '5' && c != '6'){
                System.out.println("invalid input, please input number(1-6): ");
            }else break;
        }
        return c;
    }

    /**
     * read char
     * @return char
     */
    public static char readChar(){
        String str = readKeyBoard(1,false);
        return str.charAt(0);
    }

    /**
     * read a char, if enter return, will return default value
     * @param defaultValue
     * @return
     */
    public static char readChar(char defaultValue){
        String str = readKeyBoard(1,true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     * read a integer
     * @return
     */
    public static int readInt(){
        int n = 0;
        for (; ;){
            String str = readKeyBoard(10,false);
            try {
                n = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.println("invalid input, please input a valid integer: ");
            }
        }
        return n;
    }

    /**
     * read a integer
     * @param defaultValue
     * @return
     */
    public static int rendInt(int defaultValue){
        int n;
        for (; ;){
            String str = readKeyBoard(10,true);
            if(str.equals("")){
                return defaultValue;
            }

            try {
                n = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.println("invalid input, please input a valid integer: ");
            }
        }
        return n;
    }

    /**
     * read a string with specific length
     * @param limit
     * @return string with specific length
     */
    public static String readString(int limit){
        return readKeyBoard(limit,false);
    }

    /**
     * read a string with specific length, if you don't input, will return default value
     * @param limit
     * @param defaultValue
     * @return string with specific length
     */
    public static String readString(int limit,String defaultValue){
        String str = readKeyBoard(limit,true);
        return str.equals("") ? defaultValue : str;
    }

    /**
     * read yes or no
     * @return Y or N
     */
    public static char readConfirmSelection(){
        System.out.print("Confirm to reserve(Y/N): ");
        char c;
        for(; ;){//infinite loop

            String str = readKeyBoard(1,false).toUpperCase(Locale.ROOT);
            c = str.charAt(0);
            if(c == 'Y' || c == 'N'){
                break;
            }else {
                System.out.print("invalid input, please input again(Y/N): ");
            }
        }
        return c;
    }

    /**
     * read a string with specific length
     * @param limit
     * @param flag status
     *             true：string that can be empty
     *             false：string that cannot be empty
     * @return a string with specific length
     */
    private static String readKeyBoard(int limit, boolean flag) {
        String str = "";
        do {
            str = scanner.nextLine();
            if(str.length() == 0){
                if(flag) return str;
                else continue;
            }
            if (str.length() < 1 || str.length() > limit){
                System.out.print("input length cannot greater than" + limit +"）invalid input, please input again: ");
            }else {
                return str;
            }
        }while (true);
    }
}

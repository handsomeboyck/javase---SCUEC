package com.ck.view;
import java.util.Scanner;

public class TSUtility {
	 private static Scanner scanner;

	    static {
	        scanner = new Scanner(System.in);
	    }

	    public TSUtility() {
	    }

	    public static char readMenuSelection() {
	        while(true) {
	            String str = readKeyBoard(1, false);
	            char c = str.charAt(0);
	            if (c == '1' || c == '2' || c == '3' || c == '4') {
	                return c;
	            }

	            System.out.print("ѡ��������������룺");
	        }
	    }

	    public static void readReturn() {
	        System.out.print("���س�������...");
	        readKeyBoard(100, true);
	    }

	    public static int readInt() {
	        while(true) {
	            String str = readKeyBoard(2, false);

	            try {
	                int n = Integer.parseInt(str);
	                return n;
	            } catch (NumberFormatException var3) {
	                System.out.print("��������������������룺");
	            }
	        }
	    }

	    public static char readConfirmSelection() {
	        while(true) {
	            String str = readKeyBoard(1, false).toUpperCase();
	            char c = str.charAt(0);
	            if (c == 'Y' || c == 'N') {
	                return c;
	            }

	            System.out.print("ѡ��������������룺");
	        }
	    }

	    private static String readKeyBoard(int limit, boolean blankReturn) {
	        String line = "";

	        while(scanner.hasNextLine()) {
	            line = scanner.nextLine();
	            if (line.length() == 0) {
	                if (blankReturn) {
	                    return line;
	                }
	            } else {
	                if (line.length() >= 1 && line.length() <= limit) {
	                    break;
	                }

	                System.out.print("���볤�ȣ�������" + limit + "���������������룺");
	            }
	        }

	        return line;
	    }

}

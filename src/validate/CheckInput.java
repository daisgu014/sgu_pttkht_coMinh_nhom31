package validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CheckInput {
	private static Scanner scan = new Scanner(System.in);
	public static int checkInputHotandCold() {
        Scanner scan = new Scanner(System.in);
        String input = " ";
        while (true) {
            System.out.print("Nhập lựa chọn của bạn: ");
            input = scan.nextLine();
            Pattern p = Pattern.compile("^[1-2]{1}$");
            if (p.matcher(input).find()) {
                break;
            } else {
                System.out.println("Vui lòng nhập lại!!!!!!!");
            }
        }
        return Integer.parseInt(input);
    }
	
	public static int inputMenu() {
        int input;
        while (true) {
            try {
                System.out.print("Nhập lựa chọn của bạn: ");
                input = Integer.parseInt(scan.nextLine());
                if (input >= 0 && input <= 7) {
                    break;
                } else {
                    System.err.println("Nhập các số trong menu");
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập lại lựa chọn của bạn!!!!");
            }
        }
        return input;
    }
}

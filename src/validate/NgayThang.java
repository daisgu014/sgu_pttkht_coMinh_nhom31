package validate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class NgayThang {
    private String day;
    private String month;
    private String year;


    public static int[][] arrDaysOfMonth = new
        int[][]{{31,28,31,30,31,30,31,31,30,31,30,31},
                {31,29,31,30,31,30,31,31,30,31,30,31}};
    
    public NgayThang() {
    }
    public NgayThang(String date) {
        String[] temp = date.split("/");
        this.day = temp[0];
        this.month = temp[1];
        this.year = temp[2];
    }
    public NgayThang(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public void Date() {
        LocalDate myObj = LocalDate.now(); //2021-12-11 LD
        String dateString = String.valueOf(myObj);  //2021-12-11 String
        String[] dateArray = dateString.split("-");
        this.day = dateArray[2];
        this.month = dateArray[1];
        this.year = dateArray[0];
    }
    
    public void set_Date() {
        Scanner scan = new Scanner(System.in);        
        System.out.println("Nhập năm");
        this.setYear(scan.nextLine());
        System.out.println("Nhập tháng");
        this.setMonth(scan.nextLine());
        System.out.println("Nhập ngày");
        this.setDay(scan.nextLine());
        
    }
    
    public void setStringDate() {
        System.out.println("Nhập ngày tháng năm theo định dạng dd/mm/yyyy");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String[] arrayString = s.split("/");
        this.setDay(arrayString[0]);
        this.setMonth(arrayString[1]);
        this.setYear(arrayString[2]);
    }

    @Override
    public String toString() {
        return this.day+"/"+this.month+"/"+this.year;
    }
    
    
    public static int isLeapYear(int year){
        if((year%4==0&&year%100!=0)||(year%400==0&&year%100==0))
            return 1;
        return 0;
    }

    public static boolean checkDate(String s) {
        String[] dateArray = s.split("/");
        s = "";
        s = s + dateArray[0] + dateArray[1] + dateArray[2];
        if (s.length() != 8) {
            return false;
        }
        char[] data = s.toCharArray();
        int year = Integer.parseInt(String.copyValueOf(data, 4, 4));
        int month = Integer.parseInt(String.copyValueOf(data, 2, 2));
        int day = Integer.parseInt(String.copyValueOf(data, 0, 2));
        if (month > 0 && month < 13) {
            return day > 0 && day < arrDaysOfMonth[isLeapYear(year)][month - 1];
        }
        return false;
    }
//so sánh ngày hiện tại với 1 ngày ddmmyyyy có tròn 1 năm hay chưa
    public boolean isUnderYear(){
        NgayThang now = new NgayThang();
        now.Date();
        String s = now.toString();
        String[] dateArray = s.split("/");
        s = "";
        s = s + dateArray[0] + dateArray[1] + dateArray[2];
        String sday = String.copyValueOf(s.toCharArray(),0,2);
        String smon = String.copyValueOf(s.toCharArray(),2,2);
        String syear = String.copyValueOf(s.toCharArray(),4,4);
        NgayThang tmp = new NgayThang(sday, smon, syear);
        return (Integer.parseInt(this.getYear())-Integer.parseInt(tmp.getYear()))*365
                +(Integer.parseInt(tmp.getMonth())-Integer.parseInt(tmp.getMonth()))*30
                +Integer.parseInt(this.getDay())-Integer.parseInt(this.getDay()) <= 365;
    }

   
    public boolean checkBirthdate() {
        NgayThang now = new NgayThang();
        now.Date();
        if(this.getMonth().equalsIgnoreCase(now.getMonth())) {
            return true;
        }
        return false; 
    }
}

package Management;
import static validate.CheckInput.inputMenu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


import Model.Student;
import validate.NgayThang;

public class StudentManager {
	public static ArrayList<Student> list = new ArrayList<Student>();
	
	 public static String header =String.format("%150s","**************** DANH SÁCH SINH VIÊN ********************\n\n");
	 public static String a = String.format("%15s%35s%35s%35s%35s%35s","ID", "Họ Tên", "Giới tính","Ngày sinh", "Địa chỉ", "Lớp\n");
	 public static String line = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
	 private static Scanner scan = new Scanner(System.in);
	    
	 public static int add(Student m) {
	        for(Student x : list) {
	            if(x.equals(m))
	                return 0;
	        }
	        StudentManager.list.add(m);
	        sort();
	        return 1;
	    }
	    
	    public static Student add() {
	    	Student m = new Student();
	        m.setInfo();
	        StudentManager.list.add(m);
	        sort();
	        //thêm thành công
	        System.out.println("Thêm thành viên thành công!!!!!");
	        m.writeToFile();
	        return m;
	    }   
	    
	    /*public static int deleteStudent(Student del) {
	        for(Student x : StudentManager.list)
	            if(x.equals(del)) {
	            	StudentManager.list.remove(del);
	                sort();
	                return 1;
	            }
	        System.out.println("Không tìm thấy sinh viên muốn xóa.");
	        return 0;
	    }*/
	    
	    public static void sort() {
	        Collections.sort(list, new Comparator<Student>() {
	            @Override
	            public int compare(Student o1, Student o2) {
	                return o1.getStudentID().compareToIgnoreCase(o2.getStudentID());
	            }
	        });
	    }   
	    
	    public static int update() {
	    	StudentManager.showList();
	        System.out.print("Vui lòng nhập ID thành viên:  ");
	        String idTemp = scan.nextLine();
	        for(Student x : StudentManager.list) {
	            if(x.getStudentID().equalsIgnoreCase(idTemp)) {
	                x.edit();
	                System.out.println("Chỉnh sửa thông tin thành viên thành công!!!!!");
	                System.out.println("Bạn chưa lưu dữ liệu");
	                return 1;
	            }
	        }
	        System.err.println("Không tìm thấy thành viên muốn chỉnh sửa");
	        return 0;
	    }
	    
	    //không cần đọc file, show arrayList
	    public static void showList() {
	        System.out.println(header + a + line);
	        list.forEach(x -> {
	            System.out.println(x.show());
	            System.out.println(line);
	        });
	    }
	    
	    public static int delete() {
	    	StudentManager.showList();
	        System.out.print("Vui lòng nhập ID sinh viên:  ");
	        String idTemp = scan.nextLine();
	        for(int i = 0; i < list.size(); i++)
	            if(list.get(i).getStudentID().equals(idTemp)) {
	            	StudentManager.list.remove(i);
	                System.out.println("Xóa sinh viên thành công!!!!!");
	                sort();
	                return 1;
	            }
	        System.err.println("Không tìm thấy sinh viên muốn xóa.");
	        return 0;
	    }
	    
	    public static void WirtetoFile() {
	        try {
	            FileWriter fw = new FileWriter("Student.txt");
	            BufferedWriter bw = new BufferedWriter(fw);
	            for (Student s : list) {
	                bw.write(s.toString());
	                //System.out.println(s instanceof Coffee);
	                bw.newLine();
	            }
	            bw.close();
	            fw.close();
	            //System.out.println("Ghi thành công!!!");
	        } catch (Exception e) {
	            //System.out.println("Ghi không thành công!!!!");
	        }

	    }
	    
	    public static int readMemberList() {
	        list.clear();
	        try {
	            File myObj = new File("Student.txt");
	            Scanner myReader = new Scanner(myObj);
	            while(myReader.hasNextLine()) {
	                String data = myReader.nextLine();
	                //đọc 1 dòng và add vào arrayList memberlist
	                String[] StudentInfo = data.split("@@");
	                String[] birtDate = StudentInfo[3].split("/");
	                
	                Student temp = new Student(StudentInfo[0], StudentInfo[1],StudentInfo[2], new NgayThang(birtDate[0], birtDate[1], birtDate[2]), StudentInfo[4], StudentInfo[5]);
	                StudentManager.add(temp);
	            }
	            return 1;
	        } catch (Exception e) {
	            System.out.println("Không đọc được Student.txt");
	            return 0;
	        }
	    }
	    
	    public static void saveData() {
	        System.out.println("Bạn muốn lưu dữ liệu!!!");
	        System.out.println("Nhấn 1: YES  / Nhấn 2: NO");
	        int input = Integer.parseInt(scan.nextLine());
	        if (input == 1) {
	            WirtetoFile();
	            System.out.println("GHI DỮ LIỆU THÀNH CÔNG!");
	        } else {
	            System.out.println("Dữ liệu chưa được ghi lại.");
	        }
	    }
	    
	    public static void menu() {
	        showList();
	        while (true) {
	            System.out.println("--------------------- QUẢN LÝ DANH SÁCH SINH VIÊN--------------------");
	            System.out.println("1. Thêm thành viên");
	            System.out.println("2. Chỉnh sửa thông tin sinh viên");
	            System.out.println("3. Xóa thành viên");
	            System.out.println("4. Hiển thị danh sách sinh viên");
	            System.out.println("5. Lưu dữ liệu");
	            System.out.println("6. Quay lại");
	            System.out.println("-------------------------------------------------------------------------");
	            int n=inputMenu();
	            switch(n){
	                case 1:{
	                    StudentManager.add();
	                    sort();
	                    break;
	                }
	                case 2:{
	                	StudentManager.update();                        
	                    break;
	                }
	                case 3: {
	                	StudentManager.delete();
	                    sort();
	                    break;
	                }
	                case 4: {
	                	StudentManager.showList();
	                    break;
	                }
	                case 5: {
	                	StudentManager.saveData();
	                    break;
	                }
	                case 6: {
	                	System.exit(0);
	                }
	                default:
	            }
	        }
	    }
	    
	    public static void main(String[] args) {
	    	readMemberList();
        	StudentManager.menu();
	    }
}

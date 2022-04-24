package Model;




import java.io.FileWriter;

import java.util.Scanner;

import validate.CheckInput;
import validate.CreateID;
import validate.NgayThang;


public class Student {
	private String studentID;
	protected String gender;
	private NgayThang birthDate;
	private String address;
	private String className;
	private String name;	
	
	Scanner scan = new Scanner(System.in);
	
	public Student() {
		this.studentID = CreateID.createNewSVID();
	}

	public Student(String studentID,String name,String gender, NgayThang birthDate, String address, String className) {
		this.studentID = studentID;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.className = className;
		this.name = name;
	}


	public String getStudentID() {
		return studentID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public NgayThang getBirthDate() {
		return birthDate;
	}
	
	public void setGender() {
        System.out.println("Nhập giới tính:");
        this.gender = scan.nextLine();
    }

	public void setBirthDate(NgayThang birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAddress() {
        System.out.println("Nhập quê quán:");
        this.address = scan.nextLine();
    }

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setClassName() {
        System.out.println("Nhập tên lớp:");
        this.className = scan.nextLine();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setName() {
        System.out.println("Nhập họ và tên:");
        this.name = scan.nextLine();
    }
	
	public void setInfo() {    
        System.out.println("Mời nhập tên");
        this.setName(scan.nextLine());
        
        System.out.println("Mời nhập giới tính");
        this.setGender(scan.nextLine());
        
        boolean flag = false;
        while(!flag) {
            System.out.println("Mời nhập ngày tháng năm sinh theo định dạng dd/mm/yyyy");
            String birthdate = scan.nextLine();
            if(NgayThang.checkDate(birthdate)) {
                String[] dateArray = birthdate.split("/");
                NgayThang tmp = new NgayThang(dateArray[0], dateArray[1], dateArray[2]);
                this.setBirthDate(tmp);
                flag = true;
            } else {
                System.out.println("Ngày tháng năm không tồn tại. Nhập lại");
            }
        }
        
        System.out.println("Mời quê quán");
        this.setAddress(scan.nextLine());
        
        System.out.println("Mời nhập tên lớp");
        this.setClassName(scan.nextLine());
    }
	

	@Override
	public String toString() {
		return getStudentID() +"@@"+ getName() +"@@" + getGender() + "@@" + getBirthDate()
				+ "@@" + getAddress() + "@@" + getClassName() + "@@";
	}
	
	
	public String show() {
        return String.format("%15s%35s%35s%35s%35s%35s",this.studentID, this.name, this.gender, this.birthDate, this.address, this.className);
    }
	
	 public void edit() {
	        String s = "-----------Lựa chọn thành phần cần chỉnh sửa-----------\n"+
	                     "1. Sửa họ và tên\n"+
	                     "2. Sửa ngày tháng năm sinh\n"+
	                     "3. Sửa giới tính\n"+
	                     "4. Sửa địa chỉ\n"+
	                     "5. Sửa lớp\n"+
	                     "6. Sửa tất cả thông tin trên\n"+
	        			 "7. Quay lại";
	        System.out.println(s);
	        int choice = CheckInput.inputMenu();
	        switch(choice){
	            case 1:{
	            	this.setName();
	                break;
	            }
	            case 2:{
	                NgayThang newBD = new NgayThang();
	                newBD.setStringDate();
	                this.setBirthDate(newBD);
	                break;
	            }
	            case 3:{
	            	this.setGender();
	                break;
	            }
	            case 4:{
	                this.setAddress();
	                break;
	            }
	            case 5:{
	                this.setClassName();
	                break;
	            }
	            case 6:{
	                this.setInfo();
	                break;
	            }
	            case 7:{
	            	Management.StudentManager.menu();
	                break;
	            }
	        }

	    }
	
    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("Student.txt", true);
            myWriter.write(this.toString() + "\n");
            myWriter.close();
        } catch (Exception e) {
        }
    }
	
	
}


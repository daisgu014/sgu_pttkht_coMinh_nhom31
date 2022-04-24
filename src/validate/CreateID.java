package validate;

import java.util.Iterator;

import Management.StudentManager;
import Model.Student;

public class CreateID {
	public static String createNewSVID() {
        String id = "SV";
        int ID = 1;
        String newMBID;
        boolean flag=true;
        for (Iterator<Student> it = StudentManager.list.iterator(); it.hasNext();) { 
        	Student x = it.next();
            newMBID = id + String.format("%03d", ID);
            if(x.getStudentID().equalsIgnoreCase(newMBID) && flag){
                ID++;
            }
            else{              
                flag = false;
            }    
        }
        newMBID = id + String.format("%03d",ID); 
        return newMBID;
    }
}

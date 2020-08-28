
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanhlong
 */
public class DateTimeExample {
    public static void main(String[] args) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date d= new Date();
        
       // System.out.println(dateFormat.format(d));
        
        String dateValue = "2019-09-03 19:14:00";
       try {
             Date date = dateFormat.parse(dateValue);
             System.out.println("date : "+date);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import org.aspectj.lang.JoinPoint;

import com.bettinghouse.Person;
import com.bettinghouse.User;

public aspect logger {
	File file = new File("Register.txt");
	File file2 = new File("Log.txt");
    Calendar cal;
    
    private String getNombreMetodo(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
    
    
    pointcut registrarUsuario(User user, Person person): call(* successfulSignUp(User, Person)) && args(user, person);
    
    after(User user, Person person) : registrarUsuario(user, person) {
    	this.cal = Calendar.getInstance();
    	try(PrintWriter pw=new PrintWriter(new FileOutputStream(file,true))){
    		pw.println("Usuario registrado: ["+user+"]    Fecha: ["+cal.getTime() + "]");
    		System.out.println("****Usuario ["+user.getNickname()+"] Registrado**** "+cal.getTime());
    	}catch(FileNotFoundException e){System.out.println(e.getMessage());}    
    }
    
    
    pointcut manejoSesion(User user) : call(* effectiveLog*(User)) && args(user);
    
    after(User user) : manejoSesion(user) {
    	this.cal = Calendar.getInstance();
    	try(PrintWriter pw=new PrintWriter(new FileOutputStream(file2,true))){
    		String nombreMetodo = getNombreMetodo(thisJoinPoint);
    		if (nombreMetodo.equals("effectiveLogOut")) {
    			pw.print("Sesión cerrada por usuario: [");
    			System.out.print("****Sesión Cerrada por ");
    		} else {
    			pw.print("Sesión iniciada por usuario: [");
    			System.out.print("****Sesión Iniciada por ");
    		}
    		pw.println(user.getNickname()+"]     Fecha: ["+cal.getTime() + "]");
    		System.out.println(user.getNickname()+"**** "+cal.getTime());
    	}catch(FileNotFoundException e) {System.out.println(e.getMessage());}
    }
   
}
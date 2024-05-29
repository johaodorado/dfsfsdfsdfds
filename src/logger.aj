import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import org.aspectj.lang.JoinPoint;

import com.bettinghouse.Person;
import com.bettinghouse.User;
import java.io.File;
import java.util.Calendar;

public aspect logger {
	File file = new File("Register.txt");
	File file2 = new File("Log.txt");
    Calendar calendario;
    
    private String getNombreMetodo(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
    
    
    pointcut registrarUsuario(User user, Person person): call(* successfulSignUp(User, Person)) && args(user, person);
    
    after(User user, Person person) : registrarUsuario(user, person) {
    	this.calendario = Calendar.getInstance();
    	try(PrintWriter pw=new PrintWriter(new FileOutputStream(file,true))){
    		pw.println("Usuario registrado: ["+user+"]    Fecha: ["+calendario.getTime() + "]");
    		System.out.println("****Usuario ["+user.getNickname()+"] Registrado**** "+calendario.getTime());
    	}catch(FileNotFoundException e){System.out.println(e.getMessage());}    
    }
    
    
    pointcut manejoSesion(User user) : call(* effectiveLog*(User)) && args(user);
    
    after(User user) : manejoSesion(user) {
    	this.calendario = Calendar.getInstance();
    	try(PrintWriter pw=new PrintWriter(new FileOutputStream(file2,true))){
    		String nombreMetodo = getNombreMetodo(thisJoinPoint);
    		if (nombreMetodo.equals("effectiveLogOut")) {
    			pw.print("Sesión cerrada por usuario: [");
    			System.out.print("****Sesión Cerrada por ");
    		} else {
    			pw.print("Sesión iniciada por usuario: [");
    			System.out.print("****Sesión Iniciada por ");
    		}
    		pw.println(user.getNickname()+"]     Fecha: ["+calendario.getTime() + "]");
    		System.out.println(user.getNickname()+"**** "+calendario.getTime());
    	}catch(FileNotFoundException e) {System.out.println(e.getMessage());}
    }
   
}
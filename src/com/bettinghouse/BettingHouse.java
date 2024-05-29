package com.bettinghouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BettingHouse {
	static final int EXIT = 4;
    static Map<User,Person> users = new HashMap<>();
    private static Scanner input = new Scanner(System.in);
    public static void initBettingHouse(){
    	users.put(new User("dlaborde","aspectj150"), new Person("0989632545","Dario","Laborde", 20));
    	users.put(new User("jmsantos","kamada123"), new Person("0985148965", "José", "Santos", 18));
    	users.put(new User("jorobando","empiric0"), new Person("0948514966", "Jorge", "Obando", 29));
    	users.put(new User("jorasali","nvidiatxt"), new Person("0986578963", "Jordan", "Salinas", 48));
    }

    public static void main(String[] args) {
        int opcion = 0;
        initBettingHouse();
        while(opcion != EXIT){
            opcion = primaryMenu();
        };
    }
    
    public static int primaryMenu(){
        System.out.println("Betting House EcuaAspectJ");
        System.out.println("------------------------------------");
        System.out.println("1 - Sign up");
        System.out.println("2 - Log in");
        System.out.println("3 - View registered individuals");
        System.out.println("4 - Exit");
        System.out.println("------------------------------------");
        int option = Integer.valueOf(readConsole("Options: "));
        switch (option) {
        case 1:
            System.out.println("Sign up");
            System.out.println("------------------------------------");
            signUp();
        break;
        case 2:
            System.out.println("Log in");
            System.out.println("------------------------------------");
             logIn(); 
        break;
        case 3:
            System.out.println("View registered individuals");
            viewRegisteredIndividuals();
        break;
        case EXIT:
        break;
        default:
            System.out.println("Wrong choice");
            System.out.println("------------------------------------");
            break;
        }
        return option;
    }
    
    public static int logInMenu(User user){
    	System.out.println("Welcome "+ user.getNickname() +" to EcuaAspectJ");
        System.out.println("------------------------------------");
        System.out.println("1 - To do something ");
        System.out.println("2 - Log out");
        System.out.println("------------------------------------");
        int option = Integer.valueOf(readConsole("Options: "));
        switch (option) {
        case 1:
            System.out.println("To do something");
            System.out.println("------------------------------------");
            toDoSomething();
        break;
        case 2:
        	System.out.println("Log out");
            System.out.println("------------------------------------");
            effectiveLogOut(user);
        break;
        default:
            System.out.println("Wrong choice");
            break;
        }
        return option;
    }
    
    public static String readConsole(String mensaje){
        System.out.println(mensaje);  
        String inputText = input.nextLine();           
        return inputText;
    }

    public static void signUp(){
    	String id = readConsole("Id: ");
        String firstName = readConsole("First Name: ");
        String lastName = readConsole("Last Name: ");
        int age = Integer.valueOf(readConsole("Age: "));
        String nickname = readConsole("Nickname: ");
        String password = readConsole("Password: ");
        if(age>=18) {
        	User user = new User(nickname, password);
        	Person person = new Person(id, firstName, lastName, age);
        	successfulSignUp(user, person);
        }else {
        	System.out.println("You have to be of legal age to register.");
        }
        System.out.println("------------------------------------");
    }
    
    public static void successfulSignUp(User user, Person person) {
    	users.put(user, person);
    	System.out.println("You have registered successfully.");
    }

    public static void logIn(){
    	String nickName = readConsole("Nickname: ");
        String password = readConsole("Password: ");
        User user = new User(nickName, password);
        if (!users.containsKey(user)) {
        	System.out.println("The nickname or password is incorrect");
        	System.out.println("------------------------------------");
        	return;
        }
        effectiveLogIn(user);
        int opcion = 0;
        while(opcion != 2){
            opcion = logInMenu(user);
        };
        
    }
    
    public static void effectiveLogIn(User user){
    	System.out.println(user.getNickname() + " have logged in successfully");
    }
    
    public static void toDoSomething(){
    	System.out.println("doing something...");
    	System.out.println("done");    
    	System.out.println("------------------------------------");
	}
    
    public static void effectiveLogOut(User user){
    	System.out.println(user.getNickname() + " have successfully logged out.");
    }
    
    public static void viewRegisteredIndividuals(){
        System.out.println("------------------------------------");
        for(User u:users.keySet()){  
            System.out.println(users.get(u) + ", Nickname: " + u.getNickname());
        }
        System.out.println("------------------------------------");
    }
    
}
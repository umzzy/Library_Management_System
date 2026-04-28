package util;

import model.Category;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static int readInt(String message){
        while (true){
            try{
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Invalid value, please enter a valid integer");
            }
        }
    }

    public static String readString(String message){
        while (true){
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if(value.isEmpty()){
                System.out.println("Invalid value, value cannot be empty");
                continue;
            }
            return value;
        }
    }

    public static String readDate(String message){
        while(true){
            System.out.println(message);
            String value = scanner.nextLine().trim();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMuuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

            try{
                LocalDate date = LocalDate.parse(value, inputFormatter);
                return date.format(outputFormatter);
            }catch (DateTimeException e){
                System.out.println("Invalid date format, please enter a valid date in ddMMyyyy format");
            }
        }
    }

    public static String readEmail(String message){
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if(value.isEmpty()){
                System.out.println("Invalid value, value cannot be empty");
                continue;
            }
            if(value.contains("@") && value.contains(".")){
                return value;
            }else{
                System.out.println("Invalid email format, please enter a valid email");
            }
        }
    }

    public static Category readCategory(String message){
        while(true){
            System.out.println(message);
            Category[] categories = Category.values();

            for(int i = 0; i < categories.length; i++){
                System.out.println((i+1) + ". " + categories[i].name());
            }

            int choice = readInt("Enter category number: ");
            if(choice > 0 && choice <= categories.length){
                return categories[choice - 1];
            }
            System.out.println("Invalid choice, please enter a valid category number");
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author utkua
 */
// User input/output class
public class UserIOConsoleImp implements UserIO {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        // Prompt user for input
        System.out.println(prompt);
        String output;
        // From Library java.util.Scanner
        Scanner s;
        
        do {   
            try {
                // Get input from user, return
                s = new Scanner(System.in);
                output = s.nextLine();
                return output;
            } catch (InputMismatchException e) {
                // If input is wrong format, catch InputMismatchException
                // from java.util.InputMismatchException library
                System.out.println("Input must be a String.");
            }
        } while (true);
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner s;
        int output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        Scanner s;
        int output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextInt();
                if ((output >= min) &&(output <= max)) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner s;
        double output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be a double.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        Scanner s;
        double output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextDouble();
                if ((output >= min) &&(output <= max)) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a double.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        Scanner s;
        float output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be a float.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        Scanner s;
        float output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextFloat();
                if ((output >= min) &&(output <= max)) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a float.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        Scanner s;
        long output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be a long.");
            }
        } while (true);
        
        return output;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        Scanner s;
        long output;
        
        do {   
            try {
                s = new Scanner(System.in);
                output = s.nextLong();
                if ((output >= min) &&(output <= max)) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a long.");
            }
        } while (true);
        
        return output;
    }      
}

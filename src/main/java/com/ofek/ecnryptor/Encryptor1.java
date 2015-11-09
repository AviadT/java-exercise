package com.ofek.ecnryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by aviad on 27-Oct-15.
 */

public class Encryptor1 {
    public enum Action {
        ERROR,
        ENCRYPT,
        DECRYPT,
        EXIT
    }
    static Action stringToAction(String input){
        try {
            int action = Integer.parseInt(input);
            return Action.values()[action];
        }
        catch(Exception e){
            return Action.ERROR;
        }
    }

    static String getUserInput(String print){
        String input = "error";
        System.out.print(print);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            input = br.readLine();
            if(input == null) return "error";
        } catch (IOException e){
            System.out.println("IOException: " + e);
        }
        return input;
    }

    static int checkFile(String path){
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File not found!");
            return -1;
        }
        return 0;
    }


    public static void main(String[] args){
        EncryptionAlgorithm encryptionAlgorithm = new ShiftMultiplyEncryption();
        FileEncryptor fileEncryptor = new FileEncryptor(encryptionAlgorithm);
        String menu = "List of commands:\n" +
                      "[1] Encrypt\n" +
                      "[2] Decrypt\n" +
                      "[3] Exit\n" +
                      "Enter the number of the wanted command: ";
        while (true){
            String input_action, input_path, output_path, key_path;
            input_action = getUserInput(menu);
            if(input_action.equals("error")) break;
            Action action = stringToAction(input_action);
            switch (action){
                case ERROR:
                    System.out.println("Wrong input. Please try again!");
                    break;
                case EXIT:
                    return;
                case ENCRYPT:
                    input_path = getUserInput("File to encrypt: ");
                    if(checkFile(input_path) == -1) break;
                    output_path = fileEncryptor.encryptFile(input_path);
                    System.out.println(output_path);
                    break;
                case DECRYPT:
                    input_path = getUserInput("File to decrypt: ");
                    if(checkFile(input_path) == -1) break;
                    key_path = getUserInput("Key file: ");
                    if(checkFile(key_path) == -1) break;
                    output_path = fileEncryptor.decryptFile(input_path, key_path);
                    System.out.println(output_path);
                    break;
            }
            System.out.println();
        }
    }
}

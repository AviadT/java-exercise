package com.ofek.ecnryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

    public static void main(String[] args) throws InvalidEncryptionKeyException, InvalidPathException{
 //       EncryptionAlgorithm encryptionAlgorithm = new DoubleEncryption(new ShiftUpEncryption());
        EncryptionAlgorithm encryptionAlgorithm = new ShiftUpEncryption();
        FileEncryptor fileEncryptor = new FileEncryptor(encryptionAlgorithm);
        String menu = "List of commands:\n" +
                      "[1] Encrypt\n" +
                      "[2] Decrypt\n" +
                      "[3] Exit\n" +
                      "Enter the number of the wanted command: ";
        while (true){
            String input_action, input_path, key_path;
            String[] output_path = new String[2];
            input_action = getUserInput(menu);
            if(input_action.equals("error")) break;
            Action action = stringToAction(input_action);
            switch (action){
                case ENCRYPT:
                    input_path = getUserInput("File to encrypt: ");
                    //FileOperations.createFile(input_path);
                    output_path = fileEncryptor.encryptFile(input_path);
                    System.out.println(output_path[0]);
                    System.out.println(output_path[1]);
                    break;
                case DECRYPT:
                    input_path = getUserInput("File to decrypt: ");
                    key_path = getUserInput("Key file: ");
                    output_path[0] = fileEncryptor.decryptFile(input_path, key_path);
                    System.out.println(output_path[0]);
                    break;
                case ERROR:
                    System.out.println("Wrong input");
                    break;
                case EXIT:
                    return;
            }
            System.out.println();
        }
    }
}

package com.ofek.ecnryptor;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by aviad on 10-Nov-15.
 */
abstract public class ShiftEncryption implements EncryptionAlgorithm {

    protected String[] createKey(String input_path){
        String path;
        String[] output = new String[2];
        Random random = new Random();
        path = input_path.substring(0, input_path.lastIndexOf(File.separator))
                + "\\" + "key.txt";
        output[0] = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//        output[0] = String.valueOf(1);
        output[1] = FileOperations.createFile(path);
        FileOperations.writeToFile(output[1], output[0]);
        return output;
    }

    protected String createOutput(String input_path, String addition){
        String output_path = FileOperations.addToFilename(input_path, addition);
        output_path = FileOperations.createFile(output_path);
        return output_path;
    }

    abstract protected int encryptShift(int f_char, int key);
    abstract protected int decryptShift(int f_char, int key);

    public String[] encrypt(String input_path) throws InvalidPathException{
        File input_file = new File(input_path);
        String input_full_path;
        String[] key_out;
        String [] output = new String[2];
        int key;

        FileOperations.checkFile(input_path);
        input_full_path = input_file.getAbsolutePath();
        key_out = createKey(input_full_path);
        key = Integer.parseInt(key_out[0]);
        output[1] = key_out[1];
        output[0] = createOutput(input_full_path, "_encrypted");
        FileOperations.caesarShift(input_path, output[0], key, this ,true);
        return output;
    }

    public String decrypt(String input_path, String key_path)
                    throws InvalidEncryptionKeyException, InvalidPathException{
        File input_file = new File(input_path);
        String input_full_path, output_path;
        int key;

        FileOperations.checkFile(input_path);
        FileOperations.checkFile(key_path);

        input_full_path = input_file.getAbsolutePath();
        try{
            key = FileOperations.readPositiveIntFromFile(key_path);
        } catch(InputMismatchException e){
            System.err.println("Invalid key!");
            throw new InvalidEncryptionKeyException();
        }
        output_path = createOutput(input_full_path, "_decrypted");
        FileOperations.caesarShift(input_path, output_path, key, this ,false);
        return output_path;
    }

    public int getKeyStrength(){
        return String.valueOf(Integer.MAX_VALUE).length();
    }
}

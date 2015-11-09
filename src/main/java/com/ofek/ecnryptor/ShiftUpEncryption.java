package com.ofek.ecnryptor;

import com.sun.org.apache.bcel.internal.classfile.ConstantLong;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by aviad on 27-Oct-15.
 */
public class ShiftUpEncryption implements EncryptionAlgorithm{

    private String getKeyPath(String input_path){
        String file_path, key_path;
        file_path = input_path.substring(0, input_path.lastIndexOf(File.separator));
        key_path = file_path + "\\" + "key.txt";
        return key_path;
    }

    private int createKey(String input_path){
        File key_file;
        FileWriter key_writer;
        Random random = new Random();
        int key = random.nextInt(Integer.MAX_VALUE);
        //int key = 1;

        key_file = new File(getKeyPath(input_path));
        try{
            key_file.createNewFile();
            key_writer = new FileWriter(key_file);
            key_writer.write(String.valueOf(key));
            key_writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return key;
    }

    private int getKey(String input_path){
        File key_file;
        Scanner key_reader;
        int key;
        key_file = new File(input_path);
        try{
            key_reader = new Scanner(key_file);
            key = key_reader.nextInt();
            key_reader.close();
            return key;
        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    private void caesarShift(String input_path, String output_path, int key){
        int f_char;
        Reader f_reader;
        Writer f_writer;
        try{
            f_reader = new InputStreamReader(new FileInputStream(input_path), "UTF-8");
            f_writer = new OutputStreamWriter(new FileOutputStream(output_path), "UTF-8");

            while ((f_char = f_reader.read()) != -1){
                f_writer.write(f_char + key);
            }
            f_reader.close();
            f_writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String createOutput(String input_path, String addition){
        File output_file;
        String output_path;
        int pos;
        pos = input_path.lastIndexOf(".");
        if (pos < 0) pos = input_path.length();
        output_path = input_path.substring(0, pos);
        output_path += addition + input_path.substring(pos);
        output_file = new File(output_path);
        try{
            output_file.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
            return "error";
        }
        return output_path;
    }

    public String encrypt(String input_path){
        File input_file = new File(input_path);
        String input_full_path, output_path, key_path;
        int key;

        input_full_path = input_file.getAbsolutePath();
        key = createKey(input_full_path);
        output_path = createOutput(input_full_path, "_encrypted");
        caesarShift(input_path, output_path, key);
        key_path = getKeyPath(input_full_path);
        return output_path + "\n" + key_path;
    }

    public String decrypt(String input_path, String key_path){
        File input_file = new File(input_path);
        String input_full_path, output_path;
        int key;

        input_full_path = input_file.getAbsolutePath();
        key = getKey(key_path);
        output_path = createOutput(input_full_path, "_decrypted");
        caesarShift(input_path, output_path, key * (-1));
        return output_path;
    }
}

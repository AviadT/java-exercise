package com.ofek.ecnryptor;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by aviad on 11-Nov-15.
 */
public class FileOperations {
    static public void checkFile(String path) throws InvalidPathException{
        File file = new File(path);
        if(!file.exists()){
            System.err.println("File not found!");
            throw new InvalidPathException();
        }
    }

    static public String createFile(String path){
        File output_file = new File(path);
        String tmp_path;
        int i = 0;
        while(output_file.exists()){
            tmp_path = FileOperations.addToFilename(path, "_" + Integer.toString(++i));
            output_file = new File(tmp_path);
        }
        try{
            output_file.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        return output_file.getAbsolutePath();
    }

    static public String addToFilename(String input_path, String addition){
        String output_path;
        int pos;
        pos = input_path.lastIndexOf(".");
        if (pos < 0) pos = input_path.length();
        output_path = input_path.substring(0, pos);
        output_path += addition + input_path.substring(pos);
        return output_path;
    }

    static public void writeToFile(String path, String chars){
        File file = new File(path);
        FileWriter writer;
        try{
            writer = new FileWriter(file, true);
            writer.write(chars + "\n");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static public int readPositiveIntFromFile(String path){
        File file = new File(path);
        Scanner scanner;
        int out = -1;
        try{
            scanner = new Scanner(file);
            out = scanner.nextInt();
            scanner.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return out;
    }

    static public void caesarShift(String input_path, String output_path, int key,
                                   ShiftEncryption shift, boolean encrypt){
        int f_char;
        Reader f_reader;
        Writer f_writer;
        try{
            f_reader = new InputStreamReader(new FileInputStream(input_path), "UTF-8");
            f_writer = new OutputStreamWriter(new FileOutputStream(output_path), "UTF-8");

            while ((f_char = f_reader.read()) != -1){
                if(encrypt) f_writer.write(shift.encryptShift(f_char, key));
                else f_writer.write(shift.decryptShift(f_char, key));
            }
            f_reader.close();
            f_writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static public boolean changeFilename(String path, String new_name){
        File old_file = new File(path);
        File new_file = new File(new_name);
        return old_file.renameTo(new_file);
    }

    static public boolean deleteFile(String path){
        File file = new File(path);
        return file.delete();
    }

    static public void deleteLineFromFile(String path){
        File file = new File(path);
        String tmp;
        try{
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            while (scanner.hasNextLine()){
                tmp = scanner.nextLine();
                bufferedWriter.write(tmp);
            }
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

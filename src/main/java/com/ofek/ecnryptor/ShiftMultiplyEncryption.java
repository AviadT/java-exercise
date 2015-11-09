package com.ofek.ecnryptor;

import java.io.*;

/**
 * Created by aviad on 04-Nov-15.
 */
public class ShiftMultiplyEncryption extends ShiftUpEncryption {

    private void caesarShift(String input_path, String output_path, int key){
        int f_char;
        Reader f_reader;
        Writer f_writer;
        try{
            f_reader = new InputStreamReader(new FileInputStream(input_path), "UTF-8");
            f_writer = new OutputStreamWriter(new FileOutputStream(output_path), "UTF-8");

            while ((f_char = f_reader.read()) != -1){
                f_writer.write(f_char * key);
            }
            f_reader.close();
            f_writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

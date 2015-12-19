package com.ofek.ecnryptor;

import java.io.*;

/**
 * Created by aviad on 04-Nov-15.
 */
public class ShiftMultiplyEncryption extends ShiftEncryption implements EncryptionAlgorithm{
    protected int encryptShift(int f_char, int key){
        return f_char * key;
    }

    protected int decryptShift(int f_char, int key){
        return f_char / key;
    }
}

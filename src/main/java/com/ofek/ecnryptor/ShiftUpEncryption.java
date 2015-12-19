package com.ofek.ecnryptor;

import com.sun.org.apache.bcel.internal.classfile.ConstantLong;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by aviad on 27-Oct-15.
 */
public class ShiftUpEncryption extends ShiftEncryption implements EncryptionAlgorithm{
    protected int encryptShift(int f_char, int key){
        return f_char + key;
    }

    protected int decryptShift(int f_char, int key){
        return f_char - key;
    }
}

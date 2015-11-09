package com.ofek.ecnryptor;

/**
 * Created by aviad on 04-Nov-15.
 */
public interface EncryptionAlgorithm {
    String encrypt(String input_path);
    String decrypt(String input_path, String key_path);
}

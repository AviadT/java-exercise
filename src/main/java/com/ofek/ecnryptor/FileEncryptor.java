package com.ofek.ecnryptor;

/**
 * Created by aviad on 04-Nov-15.
 */
public class FileEncryptor {
    EncryptionAlgorithm encryptionAlgorithm;
    public FileEncryptor(EncryptionAlgorithm algorithm){
        encryptionAlgorithm = algorithm;
    }

    public String[] encryptFile(String input_path) throws InvalidPathException{
        return encryptionAlgorithm.encrypt(input_path);
    }

    public String decryptFile(String input_path, String key_path)
                    throws InvalidEncryptionKeyException, InvalidPathException{
        return encryptionAlgorithm.decrypt(input_path, key_path);
    }
}

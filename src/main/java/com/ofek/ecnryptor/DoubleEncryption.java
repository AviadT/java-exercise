package com.ofek.ecnryptor;

/**
 * Created by aviad on 10-Nov-15.
 */
public class DoubleEncryption implements EncryptionAlgorithm{
    EncryptionAlgorithm encryptionAlgorithm;
    public DoubleEncryption(EncryptionAlgorithm algorithm){
        encryptionAlgorithm = algorithm;
    }
    public String[] encrypt(String input_path) throws InvalidPathException{
        String[] outs1, outs2;
        int key2;
        outs1 = encryptionAlgorithm.encrypt(input_path);
        outs2 = encryptionAlgorithm.encrypt(outs1[0]);

        FileOperations.deleteFile(outs1[0]);
        FileOperations.changeFilename(outs2[0], outs1[0]);
        key2 = FileOperations.readPositiveIntFromFile(outs2[1]);
        FileOperations.writeToFile(outs1[1], String.valueOf(key2));
        FileOperations.deleteFile(outs2[1]);

        return outs1;
    }
    
    public String decrypt(String input_path, String key_path)
                    throws InvalidEncryptionKeyException, InvalidPathException{
        String out1, out2;
        try{
            out1 = encryptionAlgorithm.decrypt(input_path, key_path);
            FileOperations.deleteLineFromFile(key_path);
            out2 = encryptionAlgorithm.decrypt(out1, key_path);
        } catch(InvalidEncryptionKeyException e){
            throw new InvalidEncryptionKeyException();
        }
        FileOperations.deleteFile(out1);
        FileOperations.changeFilename(out2, out1);

        return out1;
    }

    public int getKeyStrength(){
        return encryptionAlgorithm.getKeyStrength() * 2;
    }
}

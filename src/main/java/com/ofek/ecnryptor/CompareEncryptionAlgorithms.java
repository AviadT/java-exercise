package com.ofek.ecnryptor;

import java.util.Comparator;

/**
 * Created by aviad on 20-Dec-15.
 */
public class CompareEncryptionAlgorithms implements Comparator<EncryptionAlgorithm> {
    public int compare(EncryptionAlgorithm ea1, EncryptionAlgorithm ea2){
        return ea1.getKeyStrength() - ea2.getKeyStrength();
    }
}

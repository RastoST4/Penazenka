package com.example.penazenka.service;

import com.example.penazenka.exeption.InvalidCsvValueException;
import com.example.penazenka.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvReader {
    @Autowired
    private WalletFiller walletFiller;

    public void read(String fileName, List<Wallet> wallets) throws FileNotFoundException, InvalidCsvValueException {

        File file = ResourceUtils.getFile("classpath:" + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                walletFiller.fillWallet(Arrays.asList(values), wallets);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

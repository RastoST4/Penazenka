package com.example.penazenka.service;

import com.example.penazenka.exeption.InvalidCsvValueException;
import com.example.penazenka.model.Wallet;
import com.example.penazenka.util.WalletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class UserInput {
    private final static String FILE_NAME = "data.csv";
    @Value("${quit}")
    private String quit;
    @Autowired
    private CsvReader csvReader;

    List<Wallet> wallets = new ArrayList<>();

    public void run() {

        try {
            csvReader.read(FILE_NAME, wallets);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            return;
        } catch (InvalidCsvValueException invalidCsvValueException) {
            System.out.println(invalidCsvValueException);
            return;
        }

        while (true) {
            System.out.println("Please write name of wallet owner. or write: " +quit);
            Scanner scanner = new Scanner(System.in);
            String owner = scanner.nextLine();
            if(owner.equals(quit)){
                return;
            }
            Wallet wallet = WalletUtils.GetWalletByOwner(wallets, owner);
            if (wallet == null) {
                System.out.println(String.format("Wallet with owner : %s Not Found!", owner));
            } else {
                System.out.println("Owner :" + wallet.getOwner() + " max: " + wallet.findMaxBill() + " min:" + wallet.findMinBill());
            }
        }
    }
}

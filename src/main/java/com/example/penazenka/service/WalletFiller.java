package com.example.penazenka.service;

import com.example.penazenka.exeption.InvalidCsvValueException;
import com.example.penazenka.model.Wallet;
import com.example.penazenka.util.WalletUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class WalletFiller {
    @Value("${quit}")
    private String quit;

    public void fillWallet(List<String> data, List<Wallet> wallets) throws InvalidCsvValueException {

        String owner = data.get(0);
        Wallet wallet = WalletUtils.GetWalletByOwner(wallets, owner);

        if (wallet == null) {
            if (StringUtils.isEmpty(owner) || owner.matches(".*\\d.*") || owner.equals(quit)) {
                throw new InvalidCsvValueException("Invalid Owner name", owner);
            } else
                wallet = new Wallet(owner);
            wallets.add(wallet);
        }

        List<String> money = data.subList(1, data.size());
        for (String value : money) {
            try {
                if (value.contains("m")) {
                    String cleanValue = value.substring(0, value.length() - 1);
                    wallet.addCoin(Integer.valueOf(cleanValue));
                } else {
                    wallet.addBill(Integer.valueOf(value));
                }
            } catch (NumberFormatException e) {
                throw new InvalidCsvValueException("Invalid Money value ", value);
            }

        }

    }


}

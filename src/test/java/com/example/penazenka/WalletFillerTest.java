package com.example.penazenka;

import com.example.penazenka.exeption.InvalidCsvValueException;
import com.example.penazenka.model.Wallet;
import com.example.penazenka.service.WalletFiller;
import com.example.penazenka.util.WalletUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WalletFillerTest {
    WalletFiller walletFiller = new WalletFiller();

    @Test
    public void emptyOwner() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("", "1"), new ArrayList<>());
        });

        Assertions.assertEquals("", invalidCsvValueException.getValue());
    }

    @Test
    public void NumberInOwner() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("12Rasto", "1"), new ArrayList<>());
        });

        Assertions.assertEquals("12Rasto", invalidCsvValueException.getValue());
    }

    @Test
    public void emptyStringValue() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("Rasto", "1", ""), new ArrayList<>());
        });

        Assertions.assertEquals("", invalidCsvValueException.getValue());
    }

    @Test
    public void wordStringValue() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("Rasto", "1", "word"), new ArrayList<>());
        });

        Assertions.assertEquals("word", invalidCsvValueException.getValue());
    }

    @Test
    public void CoinsWithMoreLetterStringValue() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("Rasto", "1me", "13"), new ArrayList<>());
        });

        Assertions.assertEquals("1me", invalidCsvValueException.getValue());
    }

    @Test
    public void BillsWithMoreLetterStringValue() {

        InvalidCsvValueException invalidCsvValueException = Assertions.assertThrows(InvalidCsvValueException.class, () -> {
            this.walletFiller.fillWallet(List.of("Rasto", "1m", "13w"), new ArrayList<>());
        });

        Assertions.assertEquals("13w", invalidCsvValueException.getValue());
    }

    @Test
    public void WhenOwnerIsINMoreLinesValuesAdds() throws InvalidCsvValueException {
        List<Wallet> wallets = new ArrayList<>();
        this.walletFiller.fillWallet(List.of("Rasto", "14", "15"), wallets);
        this.walletFiller.fillWallet(List.of("Rasto", "14", "15"), wallets);
        Wallet wallet = WalletUtils.GetWalletByOwner(wallets, "Rasto");

        Assertions.assertEquals(4, wallet.getBills().size());

    }

    @Test
    public void MoreOwnersAreAdded() throws InvalidCsvValueException {
        List<Wallet> wallets = new ArrayList<>();
        this.walletFiller.fillWallet(List.of("Rasto", "14", "15"), wallets);
        this.walletFiller.fillWallet(List.of("Jano", "14", "15"), wallets);

        Assertions.assertEquals(2, wallets.size());

    }


}

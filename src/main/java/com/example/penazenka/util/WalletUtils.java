package com.example.penazenka.util;

import com.example.penazenka.model.Wallet;

import java.util.List;

public class WalletUtils {

    public static Wallet GetWalletByOwner(List<Wallet> wallets,String owner){
        return wallets.stream()
                .filter(w -> owner.equals(w.getOwner()))
                .findAny()
                .orElse(null);
    }
}

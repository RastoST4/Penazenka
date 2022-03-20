package com.example.penazenka;

import com.example.penazenka.exeption.InvalidCsvValueException;
import com.example.penazenka.model.Wallet;
import com.example.penazenka.service.CsvReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderTest {
    private static CsvReader csvReader  = new CsvReader();

    @Test
    void noCSVFile()  {
        Assertions.assertThrows(FileNotFoundException.class,()->{
            csvReader.read("noName.csv",new ArrayList<>());
        });
    }
    @Test
    void emptyCSVFile() throws FileNotFoundException, InvalidCsvValueException {
       List<Wallet> walletList  = new ArrayList<>();
        csvReader.read("dataEmpty.csv",walletList);
        Assertions.assertEquals(0,walletList.size());
    }
}

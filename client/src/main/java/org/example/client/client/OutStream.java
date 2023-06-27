package org.example.client.client;

/**Class for data input*/
public class OutStream {
    public static DataInOutStatus outputIntoCLI(String strCLI) {
        System.out.println(strCLI);
        return DataInOutStatus.SUCCESSFULLY;
    }
}

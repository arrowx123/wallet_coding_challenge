//This class serves as an exmaple about how to use the librarh


package com.guofan.wallet;

import java.sql.Timestamp;
import java.util.UUID;

public class Example {
    public static void main(String[] args){
//        Wallet wallet =  new Wallet();
        UUID idOne = UUID.randomUUID();
        String ID = idOne.toString();
        System.out.println(ID);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}
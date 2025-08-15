package com.example.hello;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class HelloService {

    private Web3j web3j;
    private Credentials credentials;
    private Hello hello;

    private static final String CONTRACT_ADDRESS_FILE = "src/main/resources/contracts/contractAddress.txt";

    @PostConstruct
    public void init() throws Exception {   //hàm test
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545")); // node Hardhat
        credentials = Credentials.create("PRIVATE_KEY_CỦA_BẠN"); // Đổi thành private key của bạn

        String contractAddress = loadContractAddress();
        if (contractAddress == null || contractAddress.isBlank ()) {  //neu chua co contract
            hello = Hello.deploy (web3j,credentials,new DefaultGasProvider (), "Create new Contract Address").send();
            contractAddress = hello.getContractAddress();
            saveContractAddress(contractAddress);
            System.out.println("Deployed contract: " + contractAddress);
        } else{ //contract da duoc deploy
            hello = Hello.load (contractAddress, web3j, credentials, new DefaultGasProvider());
            System.out.println("Loaded existing contract at: " + contractAddress);
        }
    }

    public String getMessage() throws Exception {
        try{
            return hello.message().send();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public void updateMessage(String msg) {
        try {
            hello.updateMessage (msg).send ();
        } catch (Exception e) {
            throw new RuntimeException ("Error updating message", e);
        }
    }

    private void saveContractAddress(String contractAddress) {
        try{
            Files.writeString (Path.of (CONTRACT_ADDRESS_FILE),contractAddress);
        } catch (IOException e) {
            throw new RuntimeException ("Error while saving contract address", e);
        }
    }
    private String loadContractAddress() {
        try{
            return Files.readString (Path.of (CONTRACT_ADDRESS_FILE));
        } catch (IOException e) {
            return null;
        }
    }
}

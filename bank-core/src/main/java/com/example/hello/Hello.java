package com.example.hello;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 */
@SuppressWarnings("rawtypes")
public class Hello extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b..."; // Rút gọn cho dễ nhìn

    public static final String FUNC_MESSAGE = "message";
    public static final String FUNC_UPDATEMESSAGE = "updateMessage";

    @Deprecated
    protected Hello(String contractAddress, Web3j web3j, Credentials credentials,
                    BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Hello(String contractAddress, Web3j web3j, Credentials credentials,
                    ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Hello(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                    BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Hello(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                    ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> message() {
        final Function function = new Function(
                FUNC_MESSAGE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateMessage(String _msg) {
        final Function function = new Function(
                FUNC_UPDATEMESSAGE,
                Arrays.<Type>asList(new Utf8String(_msg)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Hello load(String contractAddress, Web3j web3j, Credentials credentials,
                             BigInteger gasPrice, BigInteger gasLimit) {
        return new Hello(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Hello load(String contractAddress, Web3j web3j,
                             TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Hello(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Hello load(String contractAddress, Web3j web3j, Credentials credentials,
                             ContractGasProvider contractGasProvider) {
        return new Hello(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Hello load(String contractAddress, Web3j web3j,
                             TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Hello(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Hello> deploy(Web3j web3j, Credentials credentials,
                                           ContractGasProvider contractGasProvider, String _msg) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Utf8String(_msg)));
        return deployRemoteCall(Hello.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Hello> deploy(Web3j web3j, TransactionManager transactionManager,
                                           ContractGasProvider contractGasProvider, String _msg) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Utf8String(_msg)));
        return deployRemoteCall(Hello.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Hello> deploy(Web3j web3j, Credentials credentials,
                                           BigInteger gasPrice, BigInteger gasLimit, String _msg) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Utf8String(_msg)));
        return deployRemoteCall(Hello.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Hello> deploy(Web3j web3j, TransactionManager transactionManager,
                                           BigInteger gasPrice, BigInteger gasLimit, String _msg) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Utf8String(_msg)));
        return deployRemoteCall(Hello.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}


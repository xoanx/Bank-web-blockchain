package com.example.blockchain.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
 */
@SuppressWarnings("rawtypes")
public class PaymentContract extends Contract {
    public static final String BINARY = "﻿0x608060405234801561001057600080fd5b5061058d806100206000396000f3fe60806040526004361061001e5760003560e01c8063c3bad6ef14610023575b600080fd5b61003d6004803603810190610038919061035b565b61003f565b005b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036100ae576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016100a590610414565b60405180910390fd5b600034116100f1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016100e890610480565b60405180910390fd5b8173ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610137573d6000803e3d6000fd5b508173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fbff0538a548e4ea8a217734a505ce9d9375306f438db1247603684e165834db83484604051610197929190610527565b60405180910390a35050565b6000604051905090565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006101e2826101b7565b9050919050565b6101f2816101d7565b81146101fd57600080fd5b50565b60008135905061020f816101e9565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6102688261021f565b810181811067ffffffffffffffff8211171561028757610286610230565b5b80604052505050565b600061029a6101a3565b90506102a6828261025f565b919050565b600067ffffffffffffffff8211156102c6576102c5610230565b5b6102cf8261021f565b9050602081019050919050565b82818337600083830152505050565b60006102fe6102f9846102ab565b610290565b90508281526020810184848401111561031a5761031961021a565b5b6103258482856102dc565b509392505050565b600082601f83011261034257610341610215565b5b81356103528482602086016102eb565b91505092915050565b60008060408385031215610372576103716101ad565b5b600061038085828601610200565b925050602083013567ffffffffffffffff8111156103a1576103a06101b2565b5b6103ad8582860161032d565b9150509250929050565b600082825260208201905092915050565b7f496e76616c696420726563697069656e74000000000000000000000000000000600082015250565b60006103fe6011836103b7565b9150610409826103c8565b602082019050919050565b6000602082019050818103600083015261042d816103f1565b9050919050565b7f416d6f756e74206d757374206265203e20300000000000000000000000000000600082015250565b600061046a6012836103b7565b915061047582610434565b602082019050919050565b600060208201905081810360008301526104998161045d565b9050919050565b6000819050919050565b6104b3816104a0565b82525050565b600081519050919050565b60005b838110156104e25780820151818401526020810190506104c7565b60008484015250505050565b60006104f9826104b9565b61050381856103b7565b93506105138185602086016104c4565b61051c8161021f565b840191505092915050565b600060408201905061053c60008301856104aa565b818103602083015261054e81846104ee565b9050939250505056fea26469706673582212203c56d0c4ec782840913bb7137df2473a34837d7fc40527df126399ec06bc2b2464736f6c63430008140033\r\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_MAKEPAYMENT = "makePayment";

    public static final Event PAYMENTMADE_EVENT = new Event("PaymentMade", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected PaymentContract(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaymentContract(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaymentContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaymentContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<PaymentMadeEventResponse> getPaymentMadeEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTMADE_EVENT, transactionReceipt);
        ArrayList<PaymentMadeEventResponse> responses = new ArrayList<PaymentMadeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentMadeEventResponse typedResponse = new PaymentMadeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentMadeEventResponse getPaymentMadeEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTMADE_EVENT, log);
        PaymentMadeEventResponse typedResponse = new PaymentMadeEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.memo = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PaymentMadeEventResponse> paymentMadeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentMadeEventFromLog(log));
    }

    public Flowable<PaymentMadeEventResponse> paymentMadeEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTMADE_EVENT));
        return paymentMadeEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> makePayment(String to, String memo,
            BigInteger weiValue) {
        final Function function = new Function(
                FUNC_MAKEPAYMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.Utf8String(memo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static PaymentContract load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaymentContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaymentContract load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new PaymentContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaymentContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaymentContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaymentContract> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PaymentContract> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<PaymentContract> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PaymentContract> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class PaymentMadeEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger amount;

        public String memo;
    }
}

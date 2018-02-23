package com.poc.smart.contract.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class IntegrityStore extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b610b0f8061001e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635cc768e91461005c578063642f2eaf1461020a57806397e8554d146103bc575b600080fd5b341561006757600080fd5b6100b7600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610494565b6040518080602001877effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200180602001866000191660001916815260200185815260200184151515158152602001838103835289818151815260200191508051906020019080838360005b83811015610163578082015181840152602081019050610148565b50505050905090810190601f1680156101905780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b838110156101c95780820151818401526020810190506101ae565b50505050905090810190601f1680156101f65780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b341561021557600080fd5b61022f60048080356000191690602001909190505061076c565b6040518080602001877effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001806020018660001916600019168152602001858152602001841515151581526020018381038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103245780601f106102f957610100808354040283529160200191610324565b820191906000526020600020905b81548152906001019060200180831161030757829003601f168201915b50508381038252878181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103a75780601f1061037c576101008083540402835291602001916103a7565b820191906000526020600020905b81548152906001019060200180831161038a57829003601f168201915b50509850505050505050505060405180910390f35b34156103c757600080fd5b610492600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080357effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191690602001909190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035600019169060200190919050506107df565b005b61049c6109ac565b60006104a66109ac565b60008060006104b36109c0565b600080896040518082805190602001908083835b6020831015156104ec57805182526020820191506020810190506020830392506104c7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660001916815260200190815260200160002060c06040519081016040529081600082018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105d45780601f106105a9576101008083540402835291602001916105d4565b820191906000526020600020905b8154815290600101906020018083116105b757829003601f168201915b505050505081526020016001820160009054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106f05780601f106106c5576101008083540402835291602001916106f0565b820191906000526020600020905b8154815290600101906020018083116106d357829003601f168201915b50505050508152602001600382015460001916600019168152602001600482015481526020016005820160009054906101000a900460ff1615151515815250509050806000015181602001518260400151836060015184608001518560a001518595508393509650965096509650965096505091939550919395565b600060205280600052604060002060009150905080600001908060010160009054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000029080600201908060030154908060040154908060050160009054906101000a900460ff16905086565b6000846040518082805190602001908083835b60208310151561081757805182526020820191506020810190506020830392506107f2565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209050600080826000191660001916815260200190815260200160002060050160009054906101000a900460ff16151561087c57600080fd5b60c060405190810160405280868152602001857effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001848152602001836000191681526020014281526020016001151581525060008083600019166000191681526020019081526020016000206000820151816000019080519060200190610909929190610a2a565b5060208201518160010160006101000a81548160ff02191690837f0100000000000000000000000000000000000000000000000000000000000000900402179055506040820151816002019080519060200190610967929190610a2a565b50606082015181600301906000191690556080820151816004015560a08201518160050160006101000a81548160ff0219169083151502179055509050505050505050565b602060405190810160405280600081525090565b60c0604051908101604052806109d4610aaa565b815260200160007effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001610a0a610aaa565b815260200160008019168152602001600081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a6b57805160ff1916838001178555610a99565b82800160010185558215610a99579182015b82811115610a98578251825591602001919060010190610a7d565b5b509050610aa69190610abe565b5090565b602060405190810160405280600081525090565b610ae091905b80821115610adc576000816000905550600101610ac4565b5090565b905600a165627a7a723058204b179a914dc8b98f4b8a29badbb36b38dc479b4cbfcefbc41a500d655ad3d8590029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected IntegrityStore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IntegrityStore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>> singleTransaction(String _id) {
        final Function function = new Function("singleTransaction", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes1>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>>(
                new Callable<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<String, byte[], String, byte[], BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<String, byte[], String, byte[], BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>> transactions(byte[] param0, byte[] _delimiter, String _columns, byte[] _hash) {
        final Function function = new Function("transactions", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0), 
                new org.web3j.abi.datatypes.generated.Bytes1(_delimiter), 
                new org.web3j.abi.datatypes.Utf8String(_columns), 
                new org.web3j.abi.datatypes.generated.Bytes32(_hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes1>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>>(
                new Callable<Tuple6<String, byte[], String, byte[], BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<String, byte[], String, byte[], BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<String, byte[], String, byte[], BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> save(String _id, byte[] _delimiter, String _columns, byte[] _hash) {
        Function function = new Function(
                "save", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id), 
                new org.web3j.abi.datatypes.generated.Bytes1(_delimiter), 
                new org.web3j.abi.datatypes.Utf8String(_columns), 
                new org.web3j.abi.datatypes.generated.Bytes32(_hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<IntegrityStore> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IntegrityStore.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IntegrityStore> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IntegrityStore.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static IntegrityStore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IntegrityStore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static IntegrityStore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IntegrityStore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}

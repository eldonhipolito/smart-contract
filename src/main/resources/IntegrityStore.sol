pragma solidity 0.4.18;

contract IntegrityStore {
    
    struct Transaction {
        string id;
        bytes1 delimiter;
        string columns;
        bytes32 hash;
        uint256 timestamp;
        bool initialized;
    }
    
    mapping (bytes32 => Transaction) public transactions;
    
    function IntegrityStore() public {
        
    }
    
    function save(string _id, bytes1 _delimiter, string _columns, bytes32 _hash) public {
        bytes32 key = keccak256(_id);
        require(!transactions[key].initialized);
        
        transactions[key] = Transaction(_id, _delimiter, _columns, _hash, block.timestamp, true);
    }

    function singleTransaction(string _id) public view returns(string, bytes1, string, bytes32, uint256, bool) {
        Transaction memory t = transactions[keccak256(_id)];

        return (t.id, t.delimiter, t.columns, t.hash, t.timestamp, t.initialized);
    }
    
    
    
}

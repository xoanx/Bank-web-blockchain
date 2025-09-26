// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract PaymentContract {
    event PaymentMade(
        address indexed from,
        address indexed to,
        uint256 amount,
        string memo
    );

    function makePayment(
        address to,
        string memory memo
    ) public payable {
        require(to != address(0), "Invalid recipient");
        require(msg.value > 0, "Amount must be > 0");

        payable(to).transfer(msg.value);
        emit PaymentMade(msg.sender, to, msg.value, memo);
    }
}

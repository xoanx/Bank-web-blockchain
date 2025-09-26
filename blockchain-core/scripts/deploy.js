async function main() {
    const [deployer] = await ethers.getSigners();

    console.log("Deploying with account:", deployer.address);

    const PaymentContract = await ethers.getContractFactory("PaymentContract");
    const contract = await PaymentContract.deploy();

    await contract.deployed();

    console.log("PaymentContract deployed to:", contract.address);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });

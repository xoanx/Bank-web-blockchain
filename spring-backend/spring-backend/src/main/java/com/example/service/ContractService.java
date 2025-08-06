package com.example.service;


import com.example.dto.ContractDto;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    List<ContractDto> getAllContracts ();

    ContractDto getContractById (UUID idContract);

    ContractDto createContract (ContractDto contractDto);

    ContractDto updateContract (UUID idContract, ContractDto contractDto);

    void deleteContract (UUID idContract);
}


package com.example.service.impl;

import com.example.dto.ContractDto;
import com.example.entity.Contract;
import com.example.mapper.ContractMapper;
import com.example.repository.ContractRepository;
import com.example.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public List<ContractDto> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(ContractMapper::mapToContractDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto getContractById(UUID idContract) {
        Contract contract = contractRepository.findById(idContract)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        return ContractMapper.mapToContractDto(contract);
    }

    @Override
    public ContractDto createContract(ContractDto contractDto) {
        Contract contract = ContractMapper.mapToContract(contractDto);
        return ContractMapper.mapToContractDto(contractRepository.save(contract));
    }

    @Override
    public ContractDto updateContract(UUID idContract, ContractDto contractDto) {
        Contract existing = contractRepository.findById(idContract)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        Contract updated = ContractMapper.mapToContract(contractDto);
        updated.setIdContract(existing.getIdContract());
        return ContractMapper.mapToContractDto(contractRepository.save(updated));
    }

    @Override
    public void deleteContract(UUID idContract) {
        contractRepository.deleteById(idContract);
    }
}

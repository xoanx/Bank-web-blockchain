package com.example.service.impl;

import com.example.dto.detail.ContractDetailResponseDto;
import com.example.dto.request.AccountRequestDto;
import com.example.dto.request.ContractRequestDto;
import com.example.entity.Account;
import com.example.entity.Contract;
import com.example.entity.Person;
import com.example.enums.ContractStatus;
import com.example.mapper.ContractMapper;
import com.example.mapper.PersonMapper;
import com.example.repository.AccountRepository;
import com.example.repository.ContractRepository;
import com.example.repository.PersonRepository;
import com.example.service.ContractService;
import com.example.specidication.ContractSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

   @Override
   public ContractDetailResponseDto createContract(ContractRequestDto contractRequestDto, UUID personId){
       Person person = personRepository.findById (personId)
               .orElseThrow(()-> new RuntimeException("Person not found"));
       Account account = accountRepository.findById (contractRequestDto.getIdContract ())
               .orElseThrow (()-> new RuntimeException("Account not found"));
       Contract contract = Contract.builder ()
               .contractAddress (contractRequestDto.getContractAddress ())
               .contractName (contractRequestDto.getContractName ())
               .message (contractRequestDto.getMessage ())
               .status (ContractStatus.PENDING)
               .updateAt (LocalDateTime.now ())
               .build ();
       Contract savedContract = contractRepository.save(contract);
       return ContractMapper.contractMapToDetail (savedContract);
   }
   @Override
   public ContractDetailResponseDto getContractById(UUID contractId){
       Contract contract = contractRepository.findById (contractId)
               .orElseThrow(()-> new RuntimeException("Contract not found"));
       return ContractMapper.contractMapToDetail (contract);
   }
   @Override
   public List<ContractDetailResponseDto> getAllContracts(){
       return contractRepository.findAll()
               .stream ()
               .map (ContractMapper::contractMapToDetail)
               .toList ();
   }
   @Override
   public ContractDetailResponseDto updatedContract(UUID contractId, ContractRequestDto contractRequestDto){
       Contract contract = contractRepository.findById (contractId)
               .orElseThrow(()-> new RuntimeException("Contract not found"));
       if(contractRequestDto.getAccountId() != null){
            Account account = accountRepository.findById (contractRequestDto.getIdContract ())
                   .orElseThrow (()-> new RuntimeException("Account not found"));
            contract.setUpdatedBy (account);
       }
       if(contractRequestDto.getPersonId () != null){
            Person person = personRepository.findById (contractRequestDto.getPersonId ())
                   .orElseThrow(()-> new RuntimeException("Person not found"));
            contract.setPerson (person);
       }
       contract.setContractAddress (contractRequestDto.getContractAddress());
       contract.setContractName (contractRequestDto.getContractName());
       contract.setMessage (contractRequestDto.getMessage());
       contract.setStatus (contractRequestDto.getStatus());
       contract.setUpdateAt (LocalDateTime.now ());

       Contract saved = contractRepository.save(contract);
       return ContractMapper.contractMapToDetail (saved);
   }
    @Override
    public void deleteContract(UUID idContract) {
       Contract contract = contractRepository.findById (idContract)
                       .orElseThrow (()-> new RuntimeException("Contract not found"));
       contractRepository.deleteById (idContract);
    }
    @Override
    public void changeContractStatus(UUID contractId, ContractStatus contractStatus){
       Contract contract = contractRepository.findById (contractId)
               .orElseThrow (()-> new RuntimeException("Contract not found"));
       contract.setStatus (ContractStatus.ACTIVE);
       contract.setUpdateAt (LocalDateTime.now ());
       contractRepository.save(contract);
    }
    @Override
    public List<ContractDetailResponseDto> searchContract(ContractRequestDto contractRequestDto){
       return contractRepository.findAll (ContractSpecification.contractSpecification (contractRequestDto))
               .stream ()
               .map (ContractMapper::contractMapToDetail)
               .toList ();
    }

}

package com.example.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Contract")
    private UUID idContract;
    @Column(name = "Contract_Address", length =100, nullable = false)
    private String contractAddress;
    @Column(name = "Contract_Name", length = 50, nullable = false)
    private String contractName;
    @Column(name = "Message", length =50, nullable = false)
    private String message;
    @Column(name = "Create_At", length = 20, nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "Last_Updated_At", length = 20, nullable = false)
    private LocalDateTime lastUpdatedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;
    public enum Status {
        ACTIVE, EXPIRED
    }
    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonBackReference
    private Person person;
}

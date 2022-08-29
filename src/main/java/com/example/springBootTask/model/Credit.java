package com.example.springBootTask.model;



import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit ")
    private Integer idCredit ;
    @Column(name = "id_client")
    private Integer idClient;
    @Column(name = "amt_credit")
    private Double amtCredit;
    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "state_credit ")
    private String stateCredit ;

}

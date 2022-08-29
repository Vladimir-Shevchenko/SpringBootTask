package com.example.springBootTask.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedQuery(name = "Client.findByPh",
        query = "select client from Client client where client.phone = :phone")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer idClient;
    @Column(name = "date_curr")
    private Date dateCurr;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "address ")
    private String address ;
    @Column(name = "month_salary")
    private Double monthSalary;
    @Column(name = "curr_salary")
    private String CurrSalary;
    @Column(name = "decision")
    private String decision;
    @Column(name = "limit_itog")
    private Double limitItog;

}

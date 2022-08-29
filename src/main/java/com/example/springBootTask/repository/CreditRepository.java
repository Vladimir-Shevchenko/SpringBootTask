package com.example.springBootTask.repository;
import com.example.springBootTask.model.Client;
import com.example.springBootTask.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {

    @Query("select sum(credit.amtCredit) from Credit credit where credit.idClient = :idClient")
    Double amtOfDebt(@Param("idClient") Integer idClient);

}

package com.example.springBootTask.controller;

import com.example.springBootTask.model.Client;
import com.example.springBootTask.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class ClientController {

    private  ClientService clientService;


    @GetMapping("/client-create")
    public String createClientForm(Client client) {
        return "client-create";
    }

    @PostMapping("/client-create")
    public String createClient(@RequestParam("dateBirthday") String dateBirthday,
                               @RequestParam("requestLimit") Double requestLimit,
                               Client client) {

        boolean flag = clientService.saveClient(client, dateBirthday, requestLimit);

        if (flag) { return "ok";}
        return "error";

    }
}
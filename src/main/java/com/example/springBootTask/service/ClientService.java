package com.example.springBootTask.service;

import com.example.springBootTask.model.Client;
import com.example.springBootTask.repository.ClientRepository;
import com.example.springBootTask.repository.CreditRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class ClientService {

     ClientRepository clientRepository;
     CreditRepository creditRepository;




    public boolean saveClient(Client client, String dateBirthday, Double requestLimit) {
        boolean flag = true;
        Double salaryInUAH;
        Double currentDebt;
        Double limItog;
        Client clientBD;

        if(dateBirthday.length()<1){flag = false;}
        if(client.getCurrSalary().length()<3){flag = false;}
        if(client.getIdClient() ==null){flag = false;}
        if(client.getPhone().length()<10){flag = false;}
        if(client.getMonthSalary() ==null){flag = false;}

        salaryInUAH  =  client.getMonthSalary()  * converter(client.getCurrSalary());
        Integer id = client.getIdClient();

       currentDebt = creditRepository.amtOfDebt(id);
       currentDebt = currentDebt==null?0.0:currentDebt;

       limItog = defineK(client.getPhone()) * (salaryInUAH - currentDebt);

       clientBD = clientRepository.findClient(client.getIdClient());
       client.setDateCurr(clientBD.getDateCurr());
       client.setLimitItog(clientBD.getLimitItog());
       client.setDecision(clientBD.getDecision());

        if(limItog>requestLimit){client.setLimitItog(requestLimit);}
        if(currentDebt>(salaryInUAH * 0.6 )){client.setLimitItog(0.0);}

        LocalDate d =  LocalDate.parse(dateBirthday);
        if(d.getYear()>2004){client.setLimitItog(0.0);}

if(client.getLimitItog()>0.0){client.setDecision("accept");}
        clientRepository.save(client);

        return flag;
    }




    public double converter (String ccy){

        double usd;
        double eur;
        double btc;
        try {
            URL url = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array

                JSONObject countryData = (JSONObject) dataObject.get(0);
                usd = Double.parseDouble((String) countryData.get("sale"));

                JSONObject countryData2 = (JSONObject) dataObject.get(1);
                eur = Double.parseDouble((String) countryData2.get("sale"));

                JSONObject countryData3 = (JSONObject) dataObject.get(2);
                btc = Double.parseDouble((String) countryData3.get("sale"));

                if(ccy.equals("USD")){return usd;}
                if(ccy.equals("EURO")){return eur;}
                if(ccy.equals("BTC")){return btc;}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0.0;
    }




    public Double defineK(String phone){

        if(phone.substring(0,3).equals("067")){return 0.95;}
        if(phone.substring(0,3).equals("068")){return 0.95;}
        if(phone.substring(0,3).equals("097")){return 0.95;}
        if(phone.substring(0,3).equals("098")){return 0.95;}
        if(phone.substring(0,3).equals("050")){return 0.94;}
        if(phone.substring(0,3).equals("063")){return 0.92;}
        if(phone.substring(0,3).equals("093")){return 0.92;}

        return 0.9;
    }

}

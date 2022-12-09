package com.assignment.rest.Controller;


import com.assignment.rest.Models.SSGroupByType;
import com.assignment.rest.Models.StockRecord;
import com.assignment.rest.Repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiControllers {
    static final String DB_URL = "jdbc:mysql://localhost/record";

   @Autowired
   private RecordRepo recordRepo;

    // 1.  An API to add a new record to the dataset.
    @PostMapping(value="/save")
    public void saveRecord(@RequestBody StockRecord record) {

        recordRepo.save(record);
    }
//  2. An API to delete a record from the dataset.
    @DeleteMapping(value="/delete/{id}")

    public void deleteRecord(@PathVariable long id) {
        StockRecord deleteRecord = recordRepo.findById(id).get();
        recordRepo.delete(deleteRecord);
    }
//  3. An API to fetch SS for quote_currency_amount over the entire dataset.

    @GetMapping(value="/get")
    public List<Double> getCurrencyAmount() throws SQLException {

        List<Double> res = new ArrayList<>();
        res.add(recordRepo.getMinCurrencyAmount());
        res.add(recordRepo.getMaxCurrencyAmount());
        res.add(recordRepo.getAvgCurrencyAmount());
        return res;
    }
//  4.  An API to fetch SS for quote_currency_amount for records which satisfy "type": "stock_buy".
    @GetMapping(value="/getSSOfStockBuy")
    public List<Double> getCurrencyAmountWithStockBuyType() throws SQLException {
        List<Double> res = new ArrayList<>();
        res.add(recordRepo.getMinCurrencyAmountWithStockBuyType());
        res.add(recordRepo.getMaxCurrencyAmountWithStockBuyType());
        res.add(recordRepo.getAvgCurrencyAmountWithStockBuyType());
        return res;
    }
//    5. An API to fetch SS for quote_currency_amount for each type.
    @GetMapping(value="/getCurrencyAmountWithType")
    public String getCurrencyAmountWithType() throws SQLException {
        List<SSGroupByType> res = recordRepo.getCurrencyAmountGroupByType();
        StringBuilder sb = new StringBuilder();
        for (SSGroupByType s : res) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

// 6. An API to fetch SS for quote_currency_amount for each type and quote_currency combination.
    @GetMapping(value="/getCurrencyAmountWithTypeAndQuoteCurrency")
    public String getCurrencyAmountWithTypeAndQuoteCurrency() throws SQLException {
        List<SSGroupByType> res = recordRepo.getCurrencyAmountWithTypeAndQuoteCurrency();
        StringBuilder sb = new StringBuilder();
        for (SSGroupByType s : res) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
    public static Connection conn(){
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/recordusers?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String pass = "Lxr1994!";
            Class.forName(driver);
            Connection connect = DriverManager.getConnection(url, username, pass);

            return connect;
        }
        catch(Exception e){System.out.println(e); return null;}

    }
}

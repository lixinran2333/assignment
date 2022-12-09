package com.assignment.rest.Repo;

import com.assignment.rest.Models.SSGroupByType;
import com.assignment.rest.Models.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecordRepo  extends JpaRepository<StockRecord, Long> {
    @Query("SELECT MIN(quote_currency_amount) FROM stock_record")
    Double getMinCurrencyAmount();
    @Query("SELECT MAX(quote_currency_amount) FROM stock_record")
    Double getMaxCurrencyAmount();
    @Query("SELECT AVG(quote_currency_amount) FROM stock_record")
    Double getAvgCurrencyAmount();
    @Query("SELECT MIN(quote_currency_amount) FROM stock_record WHERE type='stock_buy'")
    Double getMinCurrencyAmountWithStockBuyType();
    @Query("SELECT MAX(quote_currency_amount) FROM stock_record WHERE type='stock_buy'")
    Double getMaxCurrencyAmountWithStockBuyType();
    @Query("SELECT AVG(quote_currency_amount) FROM stock_record WHERE type='stock_buy'")
    Double getAvgCurrencyAmountWithStockBuyType();
    @Query("SELECT new com.assignment.rest.Models.SSGroupByType(type,MIN(quote_currency_amount), MAX(quote_currency_amount), AVG(quote_currency_amount)) FROM stock_record GROUP BY type")
    List<SSGroupByType> getCurrencyAmountGroupByType();
    @Query("SELECT new com.assignment.rest.Models.SSGroupByType(CONCAT(type, quote_currency),MIN(quote_currency_amount), MAX(quote_currency_amount), AVG(quote_currency_amount)) FROM stock_record GROUP BY CONCAT(type, quote_currency)")
    List<SSGroupByType> getCurrencyAmountWithTypeAndQuoteCurrency();
}

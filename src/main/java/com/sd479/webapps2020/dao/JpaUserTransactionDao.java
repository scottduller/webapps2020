/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.dao;

import com.sd479.webapps2020.conversion.RSConversionClient;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Scott
 */
public class JpaUserTransactionDao extends JpaDao implements UserTransactionDao {

    @Override
    public BigDecimal getCurrencyConversion(String currencyFrom, String currencyTo, BigDecimal amount) {
        // Connecting to REST service
        RSConversionClient client = new RSConversionClient();

        // Get currency conversion as a String
        String conversion = client.getConversionAmount(currencyFrom, currencyTo, amount.toString());

        // Convert String to JsonObject
        JsonReader jsonReader = Json.createReader(new StringReader(conversion));
        JsonObject conversionJson = jsonReader.readObject();

        // Get converted currency as BigDecimal
        BigDecimal amountConverted = new BigDecimal(conversionJson.getJsonNumber("conversion").doubleValue(), new MathContext(13, RoundingMode.CEILING));
        amountConverted = amountConverted.setScale(2, RoundingMode.HALF_UP);

        jsonReader.close();
        client.close();

        return amountConverted;
    }

}

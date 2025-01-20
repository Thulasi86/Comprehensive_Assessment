package com.example.rest_assured_api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountryAPITest {

    // Positive test case for Spanish language API
    @Test
    public void testGetCountriesByLanguage() {
        Response response = RestAssured.get("https://restcountries.com/v3.1/lang/spanish");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 status code for Spanish language API");
        Assert.assertTrue(response.body().asString().contains("España"), "Response body should contain 'España'");
    }

    // Negative test case for invalid currency
    @Test
    public void testGetCountriesByInvalidCurrency() {
        Response response = RestAssured.get("https://restcountries.com/v3.1/lang/invalidCurrency");

        Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 status code for invalid currency");
    }

    // Positive test case for USD currency
    @Test
    public void testGetCountriesByCurrency() {
        Response response = RestAssured.get("https://restcountries.com/v3.1/currency/USD");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 status code for USD currency API");
        Assert.assertTrue(response.body().asString().contains("United States"), "Response body should contain 'United States'");
    }

    // Negative test case for empty currency parameter
    @Test
    public void testGetCountriesWithEmptyCurrency() {
        Response response = RestAssured.get("https://restcountries.com/v3.1/lang/");

        Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 status code for empty currency parameter");
    }
}

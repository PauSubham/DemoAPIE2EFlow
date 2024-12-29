package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.models.request.BookingDatesRequests;
import com.api.models.request.BookingRequest;
import com.api.models.request.TokenRequest;
import com.api.models.response.Booking;
import com.api.models.response.BookingResponse;
import com.api.models.response.TokenResponse;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingE2EFlowTest {

	@Test
	public void bookingE2ETest() {

		// Create the Booking
		BookingDatesRequests bookingDate = new BookingDatesRequests("2025-01-01", "2025-02-01");
		BookingRequest bookingRequest = new BookingRequest.Builder().bookingdates(bookingDate).firstname("Subham")
				.lastname("Paul").totalprice(15000).additionalneeds("Breakfast Lunch Dinner").depositpaid(true).build();

		RequestSpecification requestSpecs = given().contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking").body(bookingRequest);
		Response response = requestSpecs.post();
		Assert.assertEquals(response.statusCode(), 200);
		BookingResponse bookingResponse = response.as(BookingResponse.class);
		Assert.assertEquals(bookingResponse.getBooking().getFirstname(), "Subham");
		Assert.assertEquals(bookingResponse.getBooking().getLastname(), "Paul");
		Assert.assertEquals(bookingResponse.getBooking().getTotalprice(), 15000);
		Assert.assertEquals(bookingResponse.getBooking().getAdditionalneeds(), "Breakfast Lunch Dinner");
		int bookingID = bookingResponse.getBookingid();
		System.out.println("-----------------------------Booking Created----------------------------------------");
		System.out.println(response.asPrettyString());

		// Get the Booking Details
		requestSpecs = given().baseUri("https://restful-booker.herokuapp.com/booking/" + bookingID);
		response = requestSpecs.get();
		Assert.assertEquals(response.statusCode(), 200);
		Booking bookingDetails = response.as(Booking.class);
		Assert.assertEquals(bookingDetails.getFirstname(), "Subham");
		Assert.assertEquals(bookingDetails.getLastname(), "Paul");
		Assert.assertEquals(bookingDetails.getTotalprice(), 15000);
		Assert.assertEquals(bookingDetails.getAdditionalneeds(), "Breakfast Lunch Dinner");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), "2025-01-01");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), "2025-02-01");
		System.out.println("-----------------------------Get Booking Deatils-------------------------------------");
		System.out.println(response.asPrettyString());

		// Create Token to Update and Delete Records
		TokenRequest tokenRequest = new TokenRequest("admin", "password123");
		requestSpecs = given().contentType(ContentType.JSON).baseUri("https://restful-booker.herokuapp.com/auth")
				.body(tokenRequest);
		response = requestSpecs.post();
		Assert.assertEquals(response.statusCode(), 200);
		TokenResponse tokenResponse = response.as(TokenResponse.class);
		String token = "token=" + tokenResponse.getToken();

		// Update Booking Details using PUT
		BookingDatesRequests updatedBookingDate = new BookingDatesRequests("2025-02-01", "2025-02-02");
		BookingRequest updatedBookingRequest = new BookingRequest.Builder().bookingdates(updatedBookingDate)
				.firstname("Subham").lastname("Paul").totalprice(15000).additionalneeds("Breakfast Lunch Dinner")
				.depositpaid(true).build();

		requestSpecs = given().contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking/" + bookingID).header("Cookie", token)
				.body(updatedBookingRequest);
		response = requestSpecs.put();
		Assert.assertEquals(response.statusCode(), 200);
		bookingDetails = response.as(Booking.class);
		Assert.assertEquals(bookingDetails.getFirstname(), "Subham");
		Assert.assertEquals(bookingDetails.getLastname(), "Paul");
		Assert.assertEquals(bookingDetails.getTotalprice(), 15000);
		Assert.assertEquals(bookingDetails.getAdditionalneeds(), "Breakfast Lunch Dinner");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), "2025-02-01");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), "2025-02-02");
		System.out.println("--------------- Updated Checkin & Checkout using PUT -----------------------------------");
		System.out.println(response.asPrettyString());

		// Update Booking Details using PATCH
		BookingRequest updateLastName = new BookingRequest();
		updateLastName.setLastname("Adams");
		requestSpecs = given().contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking/" + bookingID).header("Cookie", token)
				.body(updateLastName);
		response = requestSpecs.patch();
		Assert.assertEquals(response.statusCode(), 200);
		bookingDetails = response.as(Booking.class);
		Assert.assertEquals(bookingDetails.getFirstname(), "Subham");
		Assert.assertEquals(bookingDetails.getLastname(), "Adams");
		Assert.assertEquals(bookingDetails.getTotalprice(), 15000);
		Assert.assertEquals(bookingDetails.getAdditionalneeds(), "Breakfast Lunch Dinner");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), "2025-02-01");
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), "2025-02-02");
		System.out.println("--------------- Updated Last Name using PATCH -----------------------------------");
		System.out.println(response.asPrettyString());

		// Delete the Booking
		requestSpecs = given().baseUri("https://restful-booker.herokuapp.com/booking/" + bookingID).header("Cookie",
				token);
		response = requestSpecs.delete();
		Assert.assertEquals(response.statusCode(), 201);
		System.out.println("------------------------Booking Deleted -------------------------");

	}

}

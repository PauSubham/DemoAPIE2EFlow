package com.api.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookingRequest {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDatesRequests bookingdates;
	private String additionalneeds;

	// Static inner Builder class
	public static class Builder {
		private String firstname;
		private String lastname;
		private int totalprice;
		private boolean depositpaid;
		private BookingDatesRequests bookingdates;
		private String additionalneeds;

		// Builder methods for each field
		public Builder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder totalprice(int totalprice) {
			this.totalprice = totalprice;
			return this;
		}

		public Builder depositpaid(boolean depositpaid) {
			this.depositpaid = depositpaid;
			return this;
		}

		public Builder bookingdates(BookingDatesRequests bookingdates) {
			this.bookingdates = bookingdates;
			return this;
		}

		public Builder additionalneeds(String additionalneeds) {
			this.additionalneeds = additionalneeds;
			return this;
		}

		// Build method to create the final BookingRequest object
		public BookingRequest build() {
			return new BookingRequest(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
		}
	}

	public BookingRequest() {

	}

	public BookingRequest(String firstname, String lastname, int totalprice, boolean depositpaid,
			BookingDatesRequests bookingdates, String additionalneeds) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public BookingDatesRequests getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDatesRequests bookingdates) {
		this.bookingdates = bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	@Override
	public String toString() {
		return "BookingRequest [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds="
				+ additionalneeds + "]";
	}

}

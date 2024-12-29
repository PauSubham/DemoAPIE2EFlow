package com.api.models.response;

public class BookingResponse {

	@Override
	public String toString() {
		return "BookingResponse [bookingid=" + bookingid + ", booking=" + booking + "]";
	}

	private int bookingid;
	private Booking booking;

	public BookingResponse() {
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}

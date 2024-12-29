package com.api.models.request;

public class BookingDatesRequests {

	private String checkin;
	private String checkout;

	public BookingDatesRequests() {

	}

	public BookingDatesRequests(String checkin, String checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "BookingDatesRequests [checkin=" + checkin + ", checkout=" + checkout + "]";
	}

}

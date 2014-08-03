package org.kepennar.sharemouz.backend;

public class ApiUrls {

    // Offers
    public static final String ROOT_URL_OFFERS = "api/offers";
    public static final String URL_OFFERS_OFFER = "/{id}";
    public static final String URL_OFFERS_OFFER_PHOTO = ROOT_URL_OFFERS + URL_OFFERS_OFFER + "/photo";

    // Reservations
    public static final String ROOT_URL_RESERVATIONS = "api/reservations";
    public static final String URL_RESERVATIONS_RESERVATION = "/{id}";
    public static final String URL_RESERVATIONS_RESERVE = "/reserve";
    public static final String URL_RESERVATIONS_BY_OFFER = "/offer/{offerId}";


}
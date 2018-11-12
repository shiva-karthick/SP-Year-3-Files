package com.sp.restaurantlist;

public class Restaurant {
    private String restaurantName = "";
    private String restaurantAddress = "";
    private String restaurantTel = "";
    private String restaurantType = "";

    // get and set name
    public String getName() {
        return (restaurantName);
    }

    public void setName(String restaurantName) {
        this.restaurantName = restaurantName;setContentView(R.layout.main);
    }

    // get and set addresses
    public String getAddress() {
        return (restaurantAddress);
    }

    public void setAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    // get and set telephone numbers
    public String getTelephone() {
        return (restaurantTel);
    }

    public void setTelephone(String restaurantTel) {
        this.restaurantTel = restaurantTel;
    }

    // get and set telephone numbers
    public String getRestaurantType() {
        return (restaurantType);
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    @Override
    public String toString() {
        return (getName());
    }

}

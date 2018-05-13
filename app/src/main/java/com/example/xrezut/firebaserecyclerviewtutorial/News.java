package com.example.xrezut.firebaserecyclerviewtutorial;

/**
 * Created by xrezut on 03/03/2018.
 */

public class News {




    private String name;
    private String address;
    private String image;
    private String url;
    private double latitude;
    private double longitude;





    public News(String name, String address, String image, String url,double latitude, double longitude) {
        this.name = name;

        this.address = address;
        this.image = image;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public News(){

    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

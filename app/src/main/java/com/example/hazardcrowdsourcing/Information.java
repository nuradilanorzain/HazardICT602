
package com.example.hazardcrowdsourcing;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Information {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("typeHazard")
    @Expose
    public String typeHazard;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("newsDate")
    @Expose
    public String newsDate;
    @SerializedName("newsTime")
    @Expose
    public String newsTime;
    @SerializedName("reporterName")
    @Expose
    public String reporterName;

}
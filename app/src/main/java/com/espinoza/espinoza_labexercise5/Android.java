package com.espinoza.espinoza_labexercise5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Android {
    int logo;
    String name, version, api, rdate, info;

    public Android(int logo, String name, String version, String api, String rdate, String info) {
        this.logo = logo;
        this.name = name;
        this.version = version;
        this.api = api;
        this.rdate = rdate;
        this.info = info;

    }

    public String getVersion()
    {
        return version;
    }

    public String getName()
    {
        return name;
    }

    public int getLogo()
    {
        return logo;
    }


    public String getApi()
    {
        return api;
    }

    public String getRdate()
    {
        return rdate;
    }

    public String getInfo() {
        return info;
    }
}

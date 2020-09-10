package com.example.sekhatapplication;

import android.graphics.Bitmap;

public class newItemProp {
    String name,price;
    Bitmap proImage;

    public newItemProp(String name, String price, Bitmap proImage) {
        this.name = name;
        this.price = price;
        this.proImage = proImage;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Bitmap getProImage() {
        return proImage;
    }
}

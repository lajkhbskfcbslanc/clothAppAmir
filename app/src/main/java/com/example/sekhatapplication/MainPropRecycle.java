package com.example.sekhatapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class MainPropRecycle {
    String name;
    String price;
    Drawable colorBack;
    Bitmap pic;
    MainPropRecycle(String name,String price,Drawable colorBack,Bitmap pic){
        this.name=name;
        this.price=price;
        this.colorBack=colorBack;
        this.pic=pic;
    }

    public Bitmap getPic() {
        return pic;
    }

    public Drawable getColorBack() {
        return colorBack;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}

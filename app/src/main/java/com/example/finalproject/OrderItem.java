package com.example.finalproject;

import android.view.View;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private int _id;
    private String _itemName;
    private String _itemPriceStr;
    private double _itemPriceDbl;
    private boolean _isChecked;

    public OrderItem(){

    }

    public void initOrderItem(){
        _itemPriceStr = "$" + Double.toString(_itemPriceDbl);
        _isChecked = false;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getItemName(){
        return _itemName;
    }

    public void setItemName(String name) {
        _itemName = name;
    }

    public void changeCheckBox(){
        _isChecked = !_isChecked;
    }

    public boolean getIsChecked(){ return _isChecked; }

    public void setItemPrice(double price){ _itemPriceDbl = price; }

    public String getItemPriceStr(){
        return _itemPriceStr;
    }

    public double getItemPriceDbl(){ return _itemPriceDbl; }
}

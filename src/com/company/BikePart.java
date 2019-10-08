package com.company;

import java.util.ArrayList;
import java.util.*;

public class BikePart {
    public String partName;
    public int partNumber;
    public double listPrice;
    public double salePrice;
    public boolean onSale;
    public int quantity;

    public BikePart(String partName, int partNumber, double listPrice, double salePrice, boolean onSale, int quantity) { //class constructors
        this.partName = partName;
        this.partNumber = partNumber;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.onSale = onSale;
        this.quantity = quantity;
    }

    /**
     * store serialized bikePart into an String array of values
     * @param serializedBikePart
     */
    public BikePart(String serializedBikePart){

        String[] values = serializedBikePart.split(",");

        this.partName = values[0];
        this.partNumber = Integer.parseInt(values[1]);
        this.listPrice = Double.parseDouble(values[2]);
        this.salePrice = Double.parseDouble(values[3]);
        this.onSale =  Boolean.parseBoolean(values[4]);
        this.quantity = Integer.parseInt(values[5]);

    }

    /**
    *convert its state to a byte stream so that the byte stream can be reverted back into a copy of the object
    *@return serializeBikePart
     */

    public String Serialize() {
        String serializeBikePart = (this.partName + "," + Integer.toString(this.partNumber) + "," + Double.toString(this.listPrice) + "," + Double.toString(this.salePrice) + "," + Boolean.toString(this.onSale) + "," + Integer.toString(this.quantity)) + ("\n");
        return serializeBikePart;
    }
}

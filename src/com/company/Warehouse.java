package com.company;

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;


public class Warehouse {

    ArrayList<BikePart> BikeParts;



    public Warehouse(){
        this.BikeParts =  new ArrayList();//warehouse array
        File warehouseDB = new File("warehouseDB.txt");

        try {
            Scanner fileReader = new Scanner(warehouseDB);

                while (fileReader.hasNextLine()) { //add files to ArrayList
                    BikePart currentPart = new BikePart(fileReader.nextLine());
                    BikeParts.add(currentPart);

                }
        }
        catch (IOException e){
            System.out.println("File not found");
        }

    }

    /**
     * method to sell a part by partNumber. If onsale displays salePrice, if not display listPrice. Displays date
     * and time of sale.
     * adjusts the quantity of the part sold
     * @param partNumber
     */

    public void sellPart(int partNumber){
        Date now = new Date();


        for(int i = 0; BikeParts.size() > i; i++){
            BikePart currentPart = BikeParts.get(i);

            if(currentPart.partNumber == partNumber) {
                if(currentPart.onSale){
                    currentPart.quantity -= 1;
                    System.out.println("You're in luck! This item is on sale! \n PartName: " + currentPart.partName + "  Sale Price: " + currentPart.salePrice + "  Sale Date and Time:" + now);
                }
                else{
                    currentPart.quantity -= 1;
                    System.out.println("PartName: " + currentPart.partName + "  List Price: " + currentPart.listPrice + "  Sale Date and Time: " + now);
                }
            }
        }

        this.save();

    }
    /**
     * method to see if partNumber in the BikeParts array is equal to the partNumber inputted by the user
     * @param  partNumber
     * @return found
     */

    public boolean findPart(int partNumber) {
        boolean found = false;
        for(int i = 0; BikeParts.size() > i; i++){
            if(BikeParts.get(i).partNumber == partNumber){//see if partNumber exists in arraylist
                found = true;
            }
        }
        return found;

    }

    /**
     * finds part by name, if the part is onsale it displays the sale price if not display
     * list price. If part is not in the warehouse display invalid part name
     * @param partName
     */
    public void findPart(String partName){
        boolean found = false;

        for(int i = 0; BikeParts.size() > i; i++){
            BikePart currentPart = BikeParts.get(i);

            if(currentPart.partName.equals(partName)) {
                found = true;
                if(currentPart.onSale){
                    System.out.println("PartName: " + currentPart.partName + "  Sale Price: " + currentPart.salePrice);
                }
                else{
                    System.out.println("PartName: " + currentPart.partName + "  List Price: " + currentPart.listPrice);
                }
            }
        }
        if(!found){
            System.out.println("Invalid part name");
        }
    }

    /**
     * method to sort the parts in the warehouse alphabetically
     */

    public void sortName(){
        Collections.sort(BikeParts,(o1, o2) -> o1.partName.compareTo(o2.partName));

        for(int i = 0; i < BikeParts.size(); i++){
            BikePart currentPart = BikeParts.get(i);
            System.out.println("Part name: " + currentPart.partName + "  Part Number: " + currentPart.partNumber + "  List Price: " + currentPart.listPrice + "  Sale Price: " + currentPart.salePrice + "  On Sale: " + currentPart.onSale + "  Quantity: " + currentPart.quantity);
        }
    }

    /**
     * method to sort the parts in the warehouse by part number
     */

    public void sortNumber(){
        Collections.sort(BikeParts,(o1, o2) -> Integer.valueOf(o1.partNumber).compareTo(o2.partNumber));

        for(int i = 0; i < BikeParts.size(); i++){
            BikePart currentPart = BikeParts.get(i);
            System.out.println("Part name: " + currentPart.partName + "  Part Number: " + currentPart.partNumber + "  List Price: " + currentPart.listPrice + "  Sale Price: " + currentPart.salePrice + "  On Sale: " + currentPart.onSale + "  Quantity: " + currentPart.quantity);
        }
    }

    /**
     * adds bikePart to the warehouse via user input, if the part is already in the warehouse
     * update listPrice, salePrice, and quantity
     * @param bikePart
     */


    public void userPart(BikePart bikePart){ //take in bikepart and add it to warehouse
        if(BikeParts.size() == 0){  //test to see if anything is in BikeParts
            BikeParts.add(bikePart);//nothing in BikeParts add userPart
        }
        else
        {
            boolean isDuplicate = false;
            for(int i = 0; BikeParts.size() > i; i++){
                if(BikeParts.get(i).partNumber == bikePart.partNumber){//this confirms duplicate
                    //update pricing/onsale/qty
                    isDuplicate = true;
                    BikeParts.get(i).quantity += BikeParts.get(i).quantity;
                    BikeParts.get(i).listPrice = BikeParts.get(i).listPrice;
                    BikeParts.get(i).salePrice = BikeParts.get(i).salePrice;
                }

            }
            if(isDuplicate == false){
                BikeParts.add(bikePart);
            }
        }

        this.save();
    }

    /**
     * method to read in inventory file to warehouseDB file. Checks for duplicates and if found handle accordingly(update
     * listPrice, salePrice, quantity)
     */

    public void readFile (){

        ArrayList<BikePart> inventory = new ArrayList<>();
        Scanner fileReader = null;
        Scanner stdin;
        String fileName;
        File bikeInfo;

        stdin = new Scanner(System.in);
        System.out.println("Enter input file name: ");
        fileName = stdin.next();

        try {
            bikeInfo = new File(fileName);
            fileReader = new Scanner(bikeInfo);
            while (fileReader.hasNextLine()) { //add files to ArrayList
                BikePart currentPart = new BikePart(fileReader.nextLine());
                inventory.add(currentPart);

            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.out.println("Please enter another file name and try again");
        }



        if(BikeParts.size() == 0){
            for(int i = 0; inventory.size() > i; i++){
                BikeParts.add(inventory.get(i));
            }
        }
        else{
            boolean isDuplicate = false;
            for(int i = 0; inventory.size() > i; i++){
                for(int j = 0; BikeParts.size() > j; j++){
                    if(inventory.get(i).partNumber == BikeParts.get(j).partNumber){//this confirms duplicate
                        //update pricing/onsale/qty
                        isDuplicate = true;
                        BikeParts.get(j).quantity += inventory.get(i).quantity;
                        BikeParts.get(j).listPrice = inventory.get(i).listPrice;
                        BikeParts.get(j).salePrice = inventory.get(i).salePrice;
                    }

                }
                if(isDuplicate == false){
                    BikeParts.add(inventory.get(i));
                }
            }
        }

        this.save();
    }

    /**
     * method to save state of warehouse file
     */
    public void save(){
        try(FileWriter fw = new FileWriter("warehouseDB.txt")) {
            for (int i = 0; BikeParts.size() > i; i++) {
                fw.write(BikeParts.get(i).Serialize());

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

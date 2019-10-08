package com.company;

/** This program is inventory management system for bike parts. It allows a user to read in an inventory file to the warehouseDB text file.
 * The user can read in a file, enter a part manually, sell a part by part number, display a part by part name, sort the inventory by part name and
 * sort the inventory by part number.
 * @author Brad Weems
 * @version 2.0
 */


import java.util.*;
public class Main {

    public static void main(String[] args) {

        int choice = 0;
        Scanner userInput = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();


        while(choice != 7) {
            System.out.println("Please enter a choice from the menu: \n 1: Read: Read an inventory delivery file \n 2: Enter: Enter a part \n 3: Sell: Sell a part \n 4: Display: Display a part \n 5: SortName: Sort parts by part name \n 6: SortNumber: Sort parts by part number \n 7: Quit: \n Enter your choice:");
            choice = userInput.nextInt();

            if (choice == 1) {
                warehouse.readFile();

            }
            if (choice == 2){

                System.out.println("Please enter a part name: ");
                String partName = userInput.next();

                System.out.println("Please enter a part number: ");
                int partNumber = userInput.nextInt();

                System.out.println("Please enter a part list price: ");
                double listPrice = userInput.nextDouble();

                System.out.println("Please enter a part sale price: ");
                double salePrice = userInput.nextDouble();

                System.out.println("Please enter if part is on sale: ");
                boolean onSale = userInput.nextBoolean();

                System.out.println("Please enter part quantity: ");
                int quantity = userInput.nextInt();

                BikePart bikePart = new BikePart(partName, partNumber, listPrice, salePrice, onSale, quantity);//create bikepart object

                warehouse.userPart(bikePart);


            }
            if (choice == 3){

                System.out.println("Please enter a part number: ");
                int partNumber = userInput.nextInt();
                if(warehouse.findPart(partNumber)){//returns true or false, if true call sell method
                    warehouse.sellPart(partNumber);
                }
                else{
                    System.out.println("Invalid part number");
                }

            }

            if (choice == 4){

                System.out.println("Please enter a part name: ");
                String partName = userInput.next();
                warehouse.findPart(partName);
            }

            if (choice == 5){
                warehouse.sortName();

            }
            if (choice == 6){
                warehouse.sortNumber();

            }
            if (choice == 7){
                System.exit(7);
            }
        }

    }
}



/*Read - prompt for a filename of the inventory delivery file. Read
the file and update your internal data structures as described, if file not
present post error message

Enter: - prompt for a part name, part number, list price, sale price, on sale, and quantity.
After gathering the user’s input, add the new bicycle part to your internal data structure

Sell: - prompt for a part number. After gathering the part number,
o    Display the part name, the part cost, which will be either the list price of sale price.
o    Display whether the part is on sale.
o    Display the time the part was sold.
o    Decrement the quantity of the part in your internal data structures.
o    If the part number is not in your data structure, display an error message.

Display: - prompt for a part name. After gathering the part name,
o    Display the product name, the product cost, which will be either the list price of sale price.
o    If the part name is not in your data structure, display an error message.

SortName: - display a list of parts sorted by part name.

SortNumber: - display a list of parts sorted by part number.

Quit: - quit your program and write the internal data structure to the file inventory.txt
so that the next time your program runs, it resumes with the inventory as saved from the previous run.
 */
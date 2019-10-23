package com.beans;

import java.sql.*;
import java.util.*;
import com.database.*;
public class Cart {
	 public ArrayList<Integer> id = new ArrayList();
	 public ArrayList<String> productCategory = new ArrayList();
	 public ArrayList<String> productName = new ArrayList();
	 public ArrayList<Double> prices = new ArrayList();
	 //public ArrayList<String> prices = new ArrayList();
	 public ArrayList<Integer> qty = new ArrayList();
	 public ArrayList<String> res = new ArrayList();
	    
	 Connection c;
	//result will store the itemid and quantity of item
	 public ArrayList<String> showProducts() {
	     for (int i = 0; i < this.id.size(); i++) {
	         System.out.println(qty.get(i) + " " + id.get(i) + " ");
	         res.add(qty.get(i) + ":" + id.get(i));
	        }
	     
	        return res;
	    }

	    public void listItemsOfCart() throws SQLException, ClassNotFoundException {

	        c = new DB_Conn().getConnection();
	        Statement st = c.createStatement();
	        for (int i = 0; i < id.size(); i++) {
	            int getItemId = id.get(i);
	            String getItemName = "SELECT  `desc` ,  `subcatg` ,  `price` FROM  `items` WHERE  `item_id` =" + getItemId + "";
	            //here desc is product name
	            //category is subcategory name
	            
	    
	            ResultSet rs = st.executeQuery(getItemName);
	            rs.next();
	            String p_name = rs.getString("desc");
	            //Double p_price = rs.getDouble("price");
	            String price = rs.getString("price");
	            Double p_price = Double.parseDouble(price);
	            String p_Category = rs.getString("subcatg");

	            productName.add(p_name);
	            productCategory.add(p_Category);
	            prices.add(p_price);
	        }
	    }

	    public ArrayList<String> getProductCategorys() {
	        return productCategory;
	    }

	    //getters for product names, prices 
	    public ArrayList<String> getProductNames() throws SQLException, ClassNotFoundException {
	        return productName;
	    }
	    //this will return a list

	    public ArrayList<Double> getPrices() throws SQLException, ClassNotFoundException {
	      
	        listItemsOfCart();
	        return prices;
	    }

	    public String getProductName(int id) throws SQLException, ClassNotFoundException {
	        //String p_name = productName.get(id);
	        //return p_name;
	        c = new DB_Conn().getConnection();
	        String getProductName = "SELECT  `desc` FROM  `items` WHERE  `item_id` =" + id + "";
	        Statement st = c.createStatement();

	        ResultSet rs = st.executeQuery(getProductName);
	        rs.next();
	        String name = rs.getString("desc");
	        return name;
	    }

	    public String getProductCategory(int id) throws SQLException, ClassNotFoundException {
	        //String p_name = productName.get(id);
	        //return p_name;
	        c = new DB_Conn().getConnection();
	        String getCategoryName = "SELECT   `subcatg`  FROM  `items` WHERE  `item_id` =" + id + "";
	        Statement st = c.createStatement();
	        ResultSet rs = st.executeQuery(getCategoryName);
	        rs.next();
	        String name = rs.getString("subcatg");
	        return name;
	    }

	    public double getProductPrice(int id) throws SQLException, ClassNotFoundException {
	        c = new DB_Conn().getConnection();
	        String getProductName = "SELECT  `price` FROM  `items` WHERE  `item_id` =" + id + "";
	        Statement st = c.createStatement();
	        ResultSet rs = st.executeQuery(getProductName);
	        rs.next();
	        String p = rs.getString("price");
	        double price = Double.parseDouble(p);
	        return price;
	    }

	    public double getTotalPriceOfCart() throws SQLException, ClassNotFoundException {
	        ArrayList<Double> t_prices = new ArrayList(); //this will hold the prices

	        //Step 1: Get the "id" inside the arraylist, 
	        //Step 2: find the corresponding matching price
	        //Step 3: multiply with the "qty"

	        //id (Product name) => retrieve price (Store it) => Multiply by *qty

	        c = new DB_Conn().getConnection();
	        Statement st = c.createStatement();
	        for (int i = 0; i < id.size(); i++) {
	            String sqlGetPrice = "SELECT  `price` FROM  `items` WHERE  `item_id` =" + id.get(i) + ";";
	            ResultSet rs = st.executeQuery(sqlGetPrice);
	            rs.next();
	            
	            String price = rs.getString("price");
	            double f = Double.parseDouble(price);
	            // Multiply by the quantity 
	            f *= qty.get(i);
	            // Add that multiplied value to prices list
	            t_prices.add(f);
	        }

	        double sum = 0;
	        for (int i = 0; i < t_prices.size(); i++) {
	            double p = t_prices.get(i);
	            sum += p;
	        }
	        //System.out.println("Sum of your cart is "+ sum);
	        return sum;
	    }

	    public ArrayList<Integer> getQty() {
	        return qty;
	    }

	    public ArrayList<Integer> getId() {
	        return id;
	    }

	    public boolean addProduct(int id) throws SQLException, ClassNotFoundException {
	        boolean added, matches;
	        //Step 1 : check for id in DATABASE
	        //Step 2 : Match the given id with database

	        c = new DB_Conn().getConnection();
	        Statement st = c.createStatement();

	        String sqlGetValidIds = "SELECT  `item_id` FROM  `items`;";
	        ResultSet rs = st.executeQuery(sqlGetValidIds);

	        while (rs.next()) {
	            int id_db = rs.getInt("item_id");
	            if (id_db == id) {
	                // id matches in the database 
	                matches = true;
	                break;
	            }
	        }


	        // STEP 3 : if arraylist contains the same id Do not insert
	        //          increment the qty, else insert into id & qty
	        if (matches = true) {
	            //insert into arraylist
	            if (this.id.contains(id)) {
	                //increment the quantity at where the product id resides
	                int index = this.id.indexOf(id);
	                this.qty.set(index, this.qty.get(index) + 1);
	            } else {
	                //Add one qty
	                this.qty.add(1);
	                //Add productID
	                this.id.add(id);
	            }
	            added = true;
	        } else {
	            //get out the function
	            //Because no product in in the db with that id
	            System.out.println("No product with that id exist in database");
	            added = false;
	        }
	        return added;
	    }

	    public boolean removeProduct(int productId) {
	        boolean removed;
	        
	        if (this.id.contains(productId)) {
	            //where is the id's position
	            //this.id.indexOf(productId);
	            int index = this.id.indexOf(productId);
	            int quantity = this.qty.get(index);
	                //remove the entire stack from all the ArrayLists 
	                this.productCategory.remove(index);
	                this.productName.remove(index);
	                this.prices.remove(index);
	                this.qty.remove(index);
	                this.id.remove(index);
	                removed = true;
	        }else {
	            removed = false;
	        }
	           
	        return removed;
	    }

	    public static void main(String args[]) throws SQLException, ClassNotFoundException {
	        try {
	            Cart c = new Cart();
	            Scanner sc = new Scanner(System.in);
	            /*
	             do {
	             System.out.println("Do u wish to continue?");
	             String choice = sc.next();
	             }while (choice.equals("1"));*/
	            String choice = "1";
	            while (choice.equals("1")) {
	                System.out.println("What do u want to buy? Give me ID");
	                int id = sc.nextInt();
	                c.addProduct(id); //Good
	                System.out.println("Do u wish to continue?");
	                choice = sc.next();
	            }

	            c.showProducts(); //Good

	            //c.listItemsOfCart();
	            ArrayList<String> productNames = c.getProductNames();
	            ArrayList<String> p_Category = c.getProductCategorys();
	            ArrayList<Double> prices1 = c.getPrices();
	            ArrayList<Integer> qty1 = c.getQty();
	            for (int i = 0; i < productNames.size(); i++) {
	                System.out.println(p_Category.get(i) + " " + productNames.get(i) + "              \t\t" + prices1.get(i) + " \t\t" + qty1.get(i) + " \t\t" + prices1.get(i) * qty1.get(i));
	            }
	            System.out.println("Total Price of the cart is :" + c.getTotalPriceOfCart());

	            choice = "1";
	            while ("1".equals(choice)) {

	                System.out.println("Give Product id to remove");
	                int id = sc.nextInt();
	                boolean removeProduct = c.removeProduct(id);
	                if (removeProduct){
	                    for (int i = 0; i < productNames.size(); i++) {
	                        System.out.println(p_Category.get(i) + " " + productNames.get(i) + "              \t\t" + prices1.get(i) + " \t\t" + qty1.get(i) + " \t\t" + prices1.get(i) * qty1.get(i));
	                        
	                        System.out.println("Total Price of the cart is :" + c.getTotalPriceOfCart());

	                    }
	                }
	                else {
	                    System.out.println("No Product to remove!!");
	                }
	            }
	            
	            //c.getTotalPriceOfCart();
	        } catch (SQLException e) {
	            System.out.println("" + e);
	        } catch (ClassNotFoundException e) {
	            System.out.println("" + e);
	        }
	    }
	}

package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

   
    public BankAccount(String email, double startingBalance) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        //check for negative starting balance
        if (startingBalance < 0) {
            throw new IllegalArgumentException("Starting balance cannot be negative");
        }
        this.email = email;
        this.balance = startingBalance;
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        }
        balance -= amount;
    }
    


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){ // no @ symbol
            return false;
        }
        else if(email == null || email.length() == 0){ // empty string
            return false;
        }
        else if (email.indexOf("@") == 0){ // missing local part
            return false;
        }
        else if (email.contains("..")){ // consecutive dots
            return false;
        }
        else if(email.lastIndexOf(".") < email.lastIndexOf("@")){ // no domain extension & accounts for just in case more than 1 @ symbol
            return false;
        }
        else if(email.indexOf('@') != email.lastIndexOf('@')) // multiple @ symbols
        {
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        }
        // Check if there are more than 2 decimal places
        String text = Double.toString(Math.abs(amount));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        
        if (text.contains(".") && decimalPlaces > 2) {
            // Handle cases like 100.0 which toString() produces
            if (text.endsWith("0") && decimalPlaces == 2) return true; 
            return false;
        }
        return true;
}
}

// transfrTo 
// isAmountValid
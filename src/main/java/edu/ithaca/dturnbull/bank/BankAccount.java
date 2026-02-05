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
        // check for negative withdrawal amount
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount cannot be negative");
        }
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new InsufficientFundsException("Not enough money");
        }
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
}
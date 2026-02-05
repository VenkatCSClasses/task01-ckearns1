package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        // Equivalence Class: Account with a positive balance (Standard case)
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);

        // Equivalence Class: Account with zero balance 
        // Border case: Minimum possible balance
        BankAccount zeroAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, zeroAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        // Equivalence Class: Normal withdrawal within balance (middle)
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), 0.001);

        // Equivalence Class: Withdrawal amount exceeding balance
        // Border case: Slightly above balance (Right side of valid/invalid border)
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));
        
        // Equivalence Class: Withdrawal of entire balance
        // Border case: Exactly the balance amount (Right edge of valid)
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance(), 0.001);

        // Equivalence Class: Withdrawal of a negative amount
        // Border case: Smallest negative value (Left side of valid/invalid border)
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.01));
    }

    @Test
    void isEmailValidTest(){
        // Equivalence class: Valid email format with @ and domain extension
        // Border case: Minimal valid email
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        
        // Equivalence class: Empty/null input
        // Border case: Minimum length (0 characters)
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        
        // Equivalence class: Missing required @ symbol
        // Not a border case
        assertFalse( BankAccount.isEmailValid("ab.com"));    // missing @ symbol
        
        // Equivalence class: Missing local part (prefix before @)
        // Border case: @ symbol at the beginning
        assertFalse( BankAccount.isEmailValid("@b.com"));   // no prefix
        
        // Equivalence class: Missing domain extension (after .)
        // Border case: No extension after domain name
        assertFalse( BankAccount.isEmailValid("a@b"));     // no domain
        
        // Equivalence class: Invalid character sequence (consecutive dots)
        // Not a border case
        assertFalse( BankAccount.isEmailValid("a@b..com")); // consecutive dots
        
        // Equivalence class: Multiple @ symbols
        // Not a border case
        assertFalse( BankAccount.isEmailValid("a@@b.com"));  // multiple @ symbols 
        
        // Missing equivalence classes/border cases:
        // - Email with special characters in local part (e.g. "a+tag@b.com")
        // - Domain with single letter (border case for domain length)
    
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        
        // Equivalence class: Invalid email provided to constructor
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("", 100));
        
        // Equivalence class: Invalid starting balance (negative)
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -10));
    }

}
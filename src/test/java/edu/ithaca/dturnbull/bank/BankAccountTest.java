package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
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
        // - Email with hyphen in domain
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}
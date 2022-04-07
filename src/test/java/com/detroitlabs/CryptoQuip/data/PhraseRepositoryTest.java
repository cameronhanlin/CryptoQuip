package com.detroitlabs.CryptoQuip.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhraseRepositoryTest {
    private PhraseRepository testShuffle = new PhraseRepository();

    @Test
    void shuffleString() {



        String result;
        String testString = "We are great!";

        result = testShuffle.shuffleString(testString);

        System.out.println("Result:" + result);
        assertFalse(testString.equals(result));


    }

    @Test
    void expecting_true_answer() {
        boolean result;
        result = testShuffle.checkIfNotShuffled("Cameron", "Ruqayyah");
        assertEquals(true, result);


    }
}
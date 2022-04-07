package com.detroitlabs.CryptoQuip.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhraseRepositoryTest {

    @Test
    void shuffleString() {


            PhraseRepository testShuffle = new PhraseRepository();
            String result;
            String testString = "We are great!";

            result = testShuffle.shuffleString(testString);

            System.out.println("Result:" + result);
            assertFalse(testString.equals(result));


    }
}
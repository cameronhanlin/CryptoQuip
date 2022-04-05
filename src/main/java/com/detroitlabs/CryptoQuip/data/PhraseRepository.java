package com.detroitlabs.CryptoQuip.data;

import com.detroitlabs.CryptoQuip.model.CharacterBank;
import com.detroitlabs.CryptoQuip.service.PhraseGeneratorService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PhraseRepository {

    PhraseGeneratorService phraseGeneratorService = new PhraseGeneratorService();
    ArrayList<CharacterBank> characterBank = new ArrayList<>();
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String scrambled;
    String thePhrase;
    String thePhraseScrambled;

    public PhraseRepository(){
        newPhrase();
    }

    public void newPhrase(){
        characterBank.clear();
        thePhrase = phraseGeneratorService.getPhrase()[0];
        System.out.println(thePhrase);  // the Phrase as it comes from the API

        thePhrase = thePhrase.toLowerCase();
        scrambled = shuffleString(alphabet);
        thePhraseScrambled = thePhrase;

        alphabet = alphabet.toLowerCase();

        for(int i=0;i<alphabet.length();i++){
            thePhraseScrambled = thePhraseScrambled.replaceAll(alphabet.substring(i,i+1),scrambled.substring(i,i+1));
        }

        alphabet = alphabet.toUpperCase();
        thePhrase = thePhrase.toUpperCase();

        System.out.println(alphabet);
        System.out.println(scrambled);
        System.out.println(thePhrase);
        System.out.println(thePhraseScrambled);

        String letter;
        for (int i=0; i<thePhrase.length();i++){
            letter = thePhrase.substring(i,i+1);
            if(letter.matches("[A-Z]")){ /// if a normal character color
                characterBank.add(new CharacterBank(letter,thePhraseScrambled.substring(i,i+1), "incorrectBlock", "unknownBlock")); //black
            } else { // if an apostrophy or period or something
                if(letter.equals(" ")){
                    characterBank.add(new CharacterBank(letter,thePhraseScrambled.substring(i,i+1), "clearBlock", "clearBlock", true, letter)); //green
                } else {
                    characterBank.add(new CharacterBank(letter,thePhraseScrambled.substring(i,i+1), "solvedBlock", "unknownBlock", true, letter)); //green
                }

            }

        }
    }

    public void userInput(String keyChar, String newChar){

        for(CharacterBank letter: characterBank){
            if(letter.getCryptoCharacter().equals(keyChar)){
                letter.setUserCharacter(newChar);
                if(letter.getCorrectCharacter().equals(newChar)){
                    letter.setSolved(true);
                    letter.setCssClass("solvedBlock");
                } else {
                    letter.setSolved(false);
                    letter.setCssClass("incorrectBlock");
                }
            }
        }

    }

    public String shuffleString(String string){
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    public String getThePhrase() {
        return thePhrase;
    }

    public String getThePhraseScrambled() {
        return thePhraseScrambled;
    }

    public ArrayList<CharacterBank> getCharacterBank(){
        return characterBank;
    }
}

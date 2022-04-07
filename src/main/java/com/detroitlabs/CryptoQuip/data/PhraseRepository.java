package com.detroitlabs.CryptoQuip.data;

import com.detroitlabs.CryptoQuip.model.CharacterBank;
import com.detroitlabs.CryptoQuip.service.PhraseGeneratorService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PhraseRepository {

    PhraseGeneratorService phraseGeneratorService = new PhraseGeneratorService();
    ArrayList<CharacterBank> characterBank = new ArrayList<>();
    ArrayList<ArrayList<CharacterBank>> characterBankLines = new ArrayList<>();
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String scrambled;
    String thePhrase;
    String thePhraseScrambled;


    public PhraseRepository(){
        newPhrase();
    }

    public void newPhrase(){
        characterBank.clear();
        characterBankLines.clear();
        thePhrase = phraseGeneratorService.getPhrase()[0];
        //thePhrase = "This is just a short line";
        //thePhrase = "America: The only country that matters. If you want to experience other ‘cultures,’ use an atlas or a ham radio.";

        thePhrase = thePhrase.toLowerCase();
        scrambled = shuffleString(alphabet);
        thePhraseScrambled = thePhrase;

        alphabet = alphabet.toLowerCase();

        for(int i=0;i<alphabet.length();i++){
            thePhraseScrambled = thePhraseScrambled.replaceAll(alphabet.substring(i,i+1),scrambled.substring(i,i+1));
        }

        alphabet = alphabet.toUpperCase();
        thePhrase = thePhrase.toUpperCase();

//        System.out.println(alphabet);
//        System.out.println(scrambled);
//        System.out.println(thePhrase);
//        System.out.println(thePhraseScrambled);

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

        characterBankLines = makeMultiLines(characterBank);
    }

    public ArrayList<ArrayList<CharacterBank>> makeMultiLines(ArrayList<CharacterBank> oneLine){
        ArrayList<ArrayList<CharacterBank>> multiLines = new ArrayList<>();

        multiLines.add(oneLine);

        int sizeOfLast = multiLines.get(multiLines.size()-1).size();

        while(sizeOfLast > 45){
            ArrayList<CharacterBank> first = new ArrayList<>();
            ArrayList<CharacterBank> second = new ArrayList<>();
            for(int i=31; i<multiLines.get(multiLines.size()-1).size(); i++){
                if (multiLines.get(multiLines.size()-1).get(i).getCorrectCharacter().equals(" ")){
                    for(int j=0; j<i; j++){
                        first.add(multiLines.get(multiLines.size()-1).get(j));
                    }
                    for(int k=i+1; k<multiLines.get(multiLines.size()-1).size(); k++){
                        second.add(multiLines.get(multiLines.size()-1).get(k));
                    }
                    i = multiLines.get(multiLines.size()-1).size();
                }
            }
            multiLines.remove(multiLines.size()-1);
            multiLines.add(first);
            multiLines.add(second);

            //outputCharacterBank(first);
            //outputCharacterBank(second);

            for(int i=0;i<multiLines.size();i++){
                outputCharacterBank(multiLines.get(i));
            }

            sizeOfLast = multiLines.get(multiLines.size()-1).size();
        }

        return multiLines;
    }

    public void outputCharacterBank(ArrayList<CharacterBank> someList){
        for(CharacterBank character : someList){
            System.out.print(character.getCorrectCharacter());
        }
        System.out.println(" ");

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

    public boolean checkWinner(){
        for(CharacterBank letter: characterBank){
            if(!letter.isSolved()){
                return false;
            }
        }
        return true;
    }

    public String shuffleString(String string){
        String original = string;
        String shuffled;
        List<String> letters = Arrays.asList(string.split(""));
        do{
            Collections.shuffle(letters);
            shuffled = "";
            for (String letter : letters) {
                shuffled += letter;
            }
        } while (checkIfNotShuffled(original, shuffled));

        return shuffled;
    }

    public boolean checkIfNotShuffled(String original, String shuffled){
        if(original.length() != shuffled.length()){
            return true;
        } else {
            for (int i=0; i<original.length(); i++){
                if(original.substring(i,i+1).equals(shuffled.substring(i,i+1))){
                    return true;
                }
            }
        }

        return false;
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

    public ArrayList<ArrayList<CharacterBank>> getCharacterBankLines() {
        return characterBankLines;
    }



}

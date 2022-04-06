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
        //thePhrase = "America: The only country that matters. If you want to experience other ‘cultures,’ use an atlas or a ham radio.";


        //System.out.println(thePhrase);  // the Phrase as it comes from the API

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

        //characterBankLines = makeMultiLines(characterBank);
    }

    //multi lines
    //if > 40, split at 33

    public ArrayList<ArrayList<CharacterBank>> makeMultiLines(ArrayList<CharacterBank> oneLine){
        ArrayList<ArrayList<CharacterBank>> multiLines = new ArrayList<>();
        ArrayList<CharacterBank> first = new ArrayList<>();
        ArrayList<CharacterBank> second = new ArrayList<>();




        multiLines.add(oneLine);
        System.out.println("the initial size is: "+oneLine.size());

        int sizeOfLast = multiLines.get(multiLines.size()-1).size();

        while(sizeOfLast > 45){
            System.out.println("Beginning of while loop, size of last multi line: "+multiLines.get(multiLines.size()-1).size());
            for(int i=31; i<multiLines.get(multiLines.size()-1).size(); i++){

                System.out.println("start of for loop "+i);
                if (multiLines.get(multiLines.size()-1).get(i).getCorrectCharacter().equals(" ")){
                    for(int j=0; j<i; j++){
                        first.add(multiLines.get(multiLines.size()-1).get(j));
                    }
                    for(int k=i; k<multiLines.get(multiLines.size()-1).size(); k++){
                        second.add(multiLines.get(multiLines.size()-1).get(k));
                    }
                    i = multiLines.get(multiLines.size()-1).size();
                }
            }
            multiLines.remove(multiLines.size()-1);
            multiLines.add(first);
            multiLines.add(second);
            System.out.println("first is sized "+first.size());
            outputCharacterBank(first);
            System.out.println("second is sized "+second.size());
            outputCharacterBank(second);

            sizeOfLast = multiLines.get(multiLines.size()-1).size();
            System.out.println("The size of the last multiLine is "+sizeOfLast);
            System.out.println(multiLines.get(multiLines.size()-1).size());
            System.out.println("multi Line has lines : "+multiLines.size());


            first.clear();
            second.clear();
            System.out.println("END OF WHILE LOOP");
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

    public ArrayList<ArrayList<CharacterBank>> getCharacterBankLines() {
        return characterBankLines;
    }



}

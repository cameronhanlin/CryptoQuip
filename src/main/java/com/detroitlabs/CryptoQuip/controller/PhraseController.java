package com.detroitlabs.CryptoQuip.controller;


import com.detroitlabs.CryptoQuip.data.PhraseRepository;
import com.detroitlabs.CryptoQuip.model.CharacterBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class PhraseController {

    @Autowired
    PhraseRepository phraseRepository;
    String keyChar = " ";
    String newChar = " ";
    boolean validInput = true;

    @RequestMapping("/")
    public String displayHome(ModelMap modelMap){
       /* modelMap.put("thePhrase", phraseRepository.getThePhrase()); //string
        modelMap.put("thePhraseScrambled", phraseRepository.getThePhraseScrambled()); //string
        modelMap.put("invalidDisplay", true);
        modelMap.put("characterBank", phraseRepository.getCharacterBank());*/

        modelMap = cycleModelMaps(modelMap);

        return "page2";
    }

    @RequestMapping("/page2")
    public String displayPage2(@RequestParam String keyChar, @RequestParam String newChar, ModelMap modelMap){

        if(!keyChar.matches("[A-Z]") || !newChar.matches("[A-Z]")){
            validInput = false;
        } else {
            phraseRepository.userInput(keyChar, newChar);
            validInput = true;
        }

        modelMap = cycleModelMaps(modelMap);

        return "page2";
    }

    @RequestMapping("/NewGame")
    public String startNewGame(ModelMap modelMap){

        phraseRepository.newPhrase();
        modelMap = cycleModelMaps(modelMap);
        return "page2";
    }


    @RequestMapping("/test")
    @ResponseBody
    public String displayCharacterBank(ModelMap modelMap){

        ArrayList<CharacterBank> theList = phraseRepository.getCharacterBank();
        System.out.println("TEST OUTPUT");
        for(CharacterBank characterBank : theList){
            System.out.println("Correct Letter: "+characterBank.getCorrectCharacter());
            System.out.println("Crypto  Letter: "+characterBank.getCryptoCharacter());
            System.out.println(" ");
        }

        return "test page";
    }

    public ModelMap cycleModelMaps(ModelMap modelMap){
        modelMap.put("thePhrase", phraseRepository.getThePhrase()); //string
        modelMap.put("thePhraseScrambled", phraseRepository.getThePhraseScrambled()); //string
        modelMap.put("invalidDisplay", true);
        modelMap.put("characterBank", phraseRepository.getCharacterBank());

        return modelMap;
    }



}

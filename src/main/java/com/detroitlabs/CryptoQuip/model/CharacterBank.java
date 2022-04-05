package com.detroitlabs.CryptoQuip.model;

public class CharacterBank {

    String correctCharacter;
    String cryptoCharacter;
    String userCharacter;
    String cssClass;
    String cryptoCssClass;
    boolean solved;

    public CharacterBank(String correctCharacter, String cryptoCharacter, String cssClass, String cryptoCssClass){
        this.correctCharacter = correctCharacter;
        this.cryptoCharacter = cryptoCharacter;
        this.cssClass = cssClass;
        this.cryptoCssClass = cryptoCssClass;
        solved = false;
        userCharacter = " ";
    }

    public CharacterBank(String correctCharacter, String cryptoCharacter, String cssClass, String cryptoCssClass, boolean solved, String userCharacter){
        this.correctCharacter = correctCharacter;
        this.cryptoCharacter = cryptoCharacter;
        this.cssClass = cssClass;
        this.cryptoCssClass = cryptoCssClass;
        this.solved = solved;
        this.userCharacter = userCharacter;
    }

    public String getCorrectCharacter() {
        return correctCharacter;
    }

    public void setCorrectCharacter(String correctCharacter) {
        this.correctCharacter = correctCharacter;
    }

    public String getCryptoCharacter() {
        return cryptoCharacter;
    }

    public void setCryptoCharacter(String cryptoCharacter) {
        this.cryptoCharacter = cryptoCharacter;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getUserCharacter() {
        return userCharacter;
    }

    public void setUserCharacter(String userCharacter) {
        this.userCharacter = userCharacter;
    }

    public String getCryptoCssClass() {
        return cryptoCssClass;
    }

    public void setCryptoCssClass(String cryptoCssClass) {
        this.cryptoCssClass = cryptoCssClass;
    }
}

package com.raeden.raidLibs.lang;

public enum LibLang {
    PREFIX("[RaidLibs] "),
    FILE_NOT_EXISTS("A file with that name doesn't exist!"),
    FILE_NOT_FOUND("A file with that name wasn't found!"),
    FILE_EXISTS("A file with that name already exists!"),
    DIR_EXISTS(),
    DIR_NOT_EXISTS(),
    DIR_NOT_FOUND(),
    private String text;
    LibLang(String text) { this.text = text;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
}

package com.raeden.raidLibs.lang;

public enum LangPaths implements LangKey {
    /*
    SYS = System
    PM = Player Msg          |       CM = Console msg
    IC = Incorrect Command   |       DIR = Directory
    CF = Create File         |       NT = Not found
    LC = Language Change
    */
    // General
    GEN_PREFIX("general.prefix"),
    GEN_CONSOLE_PREFIX("general.console-prefix"),

    // System
    SYS_LOAD_SUCCESS("system.load-success"),
    SYS_LOAD_FAILURE("system.load-failure"),
    SYS_FILE_FOUND("system.file-found"),
    SYS_FILE_NT("system.file-not-found"),
    SYS_CF("system.create-file"),
    SYS_CF_FAIL("system.create-file-fail"),
    SYS_DIR_FOUND("system.directory-found"),
    SYS_DIR_NT("system.directory-not-found"),
    SYS_DISABLE_PLUGIN("system.disable-plugin"),
    LC_SUCCESS_PM("system.lang-change.success.player-msg"),
    LC_SUCCESS_CM("system.lang-change.success.console-msg"),
    LC_FAIL_PM("system.lang-change.fail.player-msg"),
    LC_FAIL_CM("system.lang-change.fail.console-msg");
    private String path;

    LangPaths(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {return path;}

    public void setPath(String path) {this.path = path;}
}
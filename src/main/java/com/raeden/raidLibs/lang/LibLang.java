package com.raeden.raidLibs.lang;

public enum LibLang implements LangKey{
    PREFIX("[RaidLibs] "),
    UNDEFINED("<UNDEFINED>"),
    MSG_LOAD_ERROR("Failed to load message!"),

    // Operations
    OPERATION_SUCCESS("</> operation completed successfully."),
    OPERATION_CANCELLED("</> operation cancelled."),

    // Scan Operations
    BLOCK_SCAN_LOC_ERROR("Invalid location,failed to run scan operation!"),
    BLOCK_SCAN_BLOCK_ERROR("Invalid block,failed to run scan operation!"),

    // I/O & Serialization
    SAVE_SUCCESS("</> data saved successfully."),
    SAVE_FAILED("Failed to save </> data."),
    LOAD_SUCCESS("</> data loaded successfully."),
    LOAD_FAILED("Failed to load </> data."),

    // File / Directory (Existing)
    FILE_NOT_EXISTS("</> file doesn't exist!"),
    FILE_NOT_FOUND("</> file wasn't found!"),
    FILE_EXISTS("</> already exists!"),
    DIR_EXISTS("Directory </> already exists!"),
    DIR_NOT_EXISTS("Directory </> doesn't exist!"),
    DIR_NOT_FOUND("Directory </> wasn't found!"),

    // Commands & Permissions
    NO_PERMISSION("You do not have permission to do this."),
    PLAYER_ONLY("This command is for players only."),
    CONSOLE_ONLY("This command is for console only."),
    INVALID_ARGS("Invalid arguments! Usage: </>"),
    UNKNOWN_COMMAND("Unknown command."),

    // Parsing & Input
    INVALID_NUMBER("</> is not a valid number."),
    INVALID_PLAYER("Player </> is not online or invalid."),
    INVALID_MATERIAL("Material </> is invalid."),

    // Configuration & Data
    CONFIG_RELOADED("Configuration reloaded successfully."),
    CONFIG_ERROR("An error occurred while loading config."),
    DATABASE_ERROR("A database error occurred.");


    private String path;
    LibLang(String path) { this.path = path;}
    public String getPath() {return path;}
    public void setPath(String path) {this.path = path;}


}

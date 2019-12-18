package ga.tumgaming.chat;

public enum ChatType {

    LOCAL ("Local", 'f', true),
    RACE ("Race", 'a', true),
    REGION ("Region", 'f', false),
    GLOBAL ("Global", 'e', true),
    TEAM ("Team", 'c', true);

    private final char defaultColor;
    private final boolean chooseable;
    private final String clearName;

    private ChatType(String clearName, char defaultColor, boolean chooseable) {
        this.clearName = clearName;
        this.defaultColor = defaultColor;
        this.chooseable = chooseable;
    }

    public String getColor() {
        return "§" + defaultColor;
    }

    public boolean isChooseable() {
        return chooseable;
    }

    public String getClearName() {
        return clearName;
    }

    /**
     * Checks if a String matches a ChatType
     * @param str the given String
     * @return true if matches
     */
    public static boolean matches(String str) {
        for (ChatType ct : ChatType.values()) {
            if(str.toLowerCase().equals(ct.clearName.toLowerCase()))
                return true;
        }
        return false;
    }

    public static ChatType getType(String str) {
        for (ChatType ct : ChatType.values()) {
            if(str.toLowerCase().equals(ct.clearName.toLowerCase()))
                return ct;
        }
        return null;
    }

    /**
     * Wrapper for default .values() method
     * @return all ChatTypes
     */
    public static ChatType[] getAll() {
        return ChatType.values();
    }

}

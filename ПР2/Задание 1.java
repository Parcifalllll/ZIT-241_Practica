public static boolean checkEnding(String str1, String str2) {
    if (str2.length() > str1.length()) return false;
    int startIndex = str1.length() - str2.length();
    return str1.substring(startIndex).equals(str2);
}
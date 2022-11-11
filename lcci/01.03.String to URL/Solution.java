class Solution {
    public static String replaceSpaces(String S, int length) {
        StringBuffer str1 = new StringBuffer();
        for (char c:S.toCharArray()){
            if (c == ' '){
                str1.append("%20");
            } else{
                str1.append(c);
            }
        }
        return str1.toString();
    }
}
class Solution {
    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);
        return i == -1 ? word : new StringBuilder(word.substring(0, i + 1)).reverse().append(word.substring(i + 1)).toString();
    }
}
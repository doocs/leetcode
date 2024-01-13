class Solution {
    public String reversePrefix(String word, char ch) {
        int j = word.indexOf(ch);
        if (j == -1) {
            return word;
        }
        return new StringBuilder(word.substring(0, j + 1))
            .reverse()
            .append(word.substring(j + 1))
            .toString();
    }
}
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int n = 9;
        firstWord = fillA(firstWord, n);
        secondWord = fillA(secondWord, n);
        targetWord = fillA(targetWord, n);
        int carry = 0;
        while (--n >= 0) {
            int i = firstWord.charAt(n) - 'a';
            int j = secondWord.charAt(n) - 'a';
            int s = i + j + carry;
            if (targetWord.charAt(n) != (char) ('a' + (s % 10))) {
                return false;
            }
            carry = s / 10;
        }
        return true;
    }
    
    private String fillA(String word, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - word.length(); ++i) {
            sb.append("a");
        }
        sb.append(word);
        return sb.toString();
    }
}
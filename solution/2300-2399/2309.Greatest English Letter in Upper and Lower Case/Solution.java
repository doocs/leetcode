class Solution {
    public String greatestLetter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                cnt[c - 'a'] |= 1;
            } else if (Character.isUpperCase(c)) {
                cnt[c - 'A'] |= 2;
            }
        }
        for (int i = 25; i >= 0; --i) {
            if (cnt[i] == 3) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }
}
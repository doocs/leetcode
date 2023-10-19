class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] s = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (String w : text.split(" ")) {
            for (char c : w.toCharArray()) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }
}
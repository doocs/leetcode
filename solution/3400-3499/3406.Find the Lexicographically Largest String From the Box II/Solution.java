class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        String s = lastSubstring(word);
        return s.substring(0, Math.min(s.length(), word.length() - numFriends + 1));
    }

    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;
        while (j + k < n) {
            int d = s.charAt(i + k) - s.charAt(j + k);
            if (d == 0) {
                ++k;
            } else if (d < 0) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
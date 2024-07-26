class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        int x = 0, y = 0;
        for (int i = 0; i < word1.length(); ++i) {
            if (++cnt1[word1.charAt(i) - 'a'] == 1) {
                ++x;
            }
        }
        for (int i = 0; i < word2.length(); ++i) {
            if (++cnt2[word2.charAt(i) - 'a'] == 1) {
                ++y;
            }
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    if (i == j) {
                        if (x == y) {
                            return true;
                        }
                    } else {
                        int a = x - (cnt1[i] == 1 ? 1 : 0) + (cnt1[j] == 0 ? 1 : 0);
                        int b = y - (cnt2[j] == 1 ? 1 : 0) + (cnt2[i] == 0 ? 1 : 0);
                        if (a == b) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
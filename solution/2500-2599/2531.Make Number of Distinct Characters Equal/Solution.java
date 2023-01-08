class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < word1.length(); ++i) {
            ++cnt1[word1.charAt(i) - 'a'];
        }
        for (int i = 0; i < word2.length(); ++i) {
            ++cnt2[word2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    --cnt1[i];
                    --cnt2[j];
                    ++cnt1[j];
                    ++cnt2[i];
                    int d = 0;
                    for (int k = 0; k < 26; ++k) {
                        if (cnt1[k] > 0) {
                            ++d;
                        }
                        if (cnt2[k] > 0) {
                            --d;
                        }
                    }
                    if (d == 0) {
                        return true;
                    }
                    ++cnt1[i];
                    ++cnt2[j];
                    --cnt1[j];
                    --cnt2[i];
                }
            }
        }
        return false;
    }
}
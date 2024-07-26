class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        int x = 0, y = 0;
        for (char& c : word1) {
            if (++cnt1[c - 'a'] == 1) {
                ++x;
            }
        }
        for (char& c : word2) {
            if (++cnt2[c - 'a'] == 1) {
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
};
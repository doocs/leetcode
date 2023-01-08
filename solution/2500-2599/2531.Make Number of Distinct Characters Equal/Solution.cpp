class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : word1) {
            ++cnt1[c - 'a'];
        }
        for (char& c : word2) {
            ++cnt2[c - 'a'];
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
};
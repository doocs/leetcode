class Solution {
public:
    bool checkAlmostEquivalent(string word1, string word2) {
        vector<int> counter(26);
        for (char& c : word1) ++counter[c - 'a'];
        for (char& c : word2) --counter[c - 'a'];
        for (int i = 0; i < 26; ++i)
            if (abs(counter[i]) > 3)
                return 0;
        return 1;
    }
};
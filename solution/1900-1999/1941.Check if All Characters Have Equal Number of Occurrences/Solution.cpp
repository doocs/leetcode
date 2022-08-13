class Solution {
public:
    bool areOccurrencesEqual(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        unordered_set<int> ss;
        for (int& v : cnt)
            if (v) ss.insert(v);
        return ss.size() == 1;
    }
};
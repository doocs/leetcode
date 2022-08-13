class Solution {
public:
    bool areSentencesSimilar(vector<string>& sentence1, vector<string>& sentence2, vector<vector<string>>& similarPairs) {
        int m = sentence1.size(), n = sentence2.size();
        if (m != n) return false;
        unordered_set<string> s;
        for (auto e : similarPairs) s.insert(e[0] + "." + e[1]);
        for (int i = 0; i < n; ++i) {
            string a = sentence1[i], b = sentence2[i];
            if (a != b && !s.count(a + "." + b) && !s.count(b + "." + a)) return false;
        }
        return true;
    }
};
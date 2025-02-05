class Solution {
public:
    bool areSentencesSimilar(vector<string>& sentence1, vector<string>& sentence2, vector<vector<string>>& similarPairs) {
        if (sentence1.size() != sentence2.size()) {
            return false;
        }
        unordered_set<string> s;
        for (const auto& p : similarPairs) {
            s.insert(p[0] + "#" + p[1]);
            s.insert(p[1] + "#" + p[0]);
        }
        for (int i = 0; i < sentence1.size(); ++i) {
            if (sentence1[i] != sentence2[i] && !s.contains(sentence1[i] + "#" + sentence2[i])) {
                return false;
            }
        }
        return true;
    }
};

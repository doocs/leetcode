class Solution {
public:
    bool equalFrequency(string word) {
        for (int i = 0; i < word.size(); ++i) {
            int cnt[26] = {0};
            for (int j = 0; j < word.size(); ++j) {
                if (j != i) {
                    ++cnt[word[j] - 'a'];
                }
            }
            unordered_set<int> vis;
            for (int v : cnt) {
                if (v) {
                    vis.insert(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
};
class Solution {
public:
    int wordCount(vector<string>& startWords, vector<string>& targetWords) {
        unordered_set<int> s;
        for (auto& word : startWords) {
            int mask = 0;
            for (char c : word)
                mask |= (1 << (c - 'a'));
            s.insert(mask);
        }
        int ans = 0;
        for (auto& word : targetWords) {
            int mask = 0;
            for (char c : word)
                mask |= (1 << (c - 'a'));
            for (char c : word) {
                int t = mask ^ (1 << (c - 'a'));
                if (s.count(t)) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
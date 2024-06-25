class Solution {
public:
    int wordCount(vector<string>& startWords, vector<string>& targetWords) {
        unordered_set<int> s;
        for (auto& w : startWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            s.insert(x);
        }
        int ans = 0;
        for (auto& w : targetWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            for (char c : w) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
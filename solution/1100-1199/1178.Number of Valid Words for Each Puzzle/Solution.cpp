class Solution {
public:
    vector<int> findNumOfValidWords(vector<string>& words, vector<string>& puzzles) {
        unordered_map<int, int> cnt;
        for (auto& w : words) {
            int mask = 0;
            for (char& c : w) {
                mask |= 1 << (c - 'a');
            }
            cnt[mask]++;
        }
        vector<int> ans;
        for (auto& p : puzzles) {
            int mask = 0;
            for (char& c : p) {
                mask |= 1 << (c - 'a');
            }
            int x = 0;
            int i = p[0] - 'a';
            for (int j = mask; j; j = (j - 1) & mask) {
                if (j >> i & 1) {
                    x += cnt[j];
                }
            }
            ans.push_back(x);
        }
        return ans;
    }
};
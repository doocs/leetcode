class Solution {
public:
    int similarPairs(vector<string>& words) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (const auto& s : words) {
            int x = 0;
            for (auto& c : s) {
                x |= 1 << (c - 'a');
            }
            ans += cnt[x]++;
        }
        return ans;
    }
};

class Solution {
public:
    int mostFrequentEven(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int v : nums) {
            if (v % 2 == 0) {
                ++cnt[v];
            }
        }
        int ans = -1, mx = 0;
        for (auto [v, t] : cnt) {
            if (mx < t || (mx == t && ans > v)) {
                mx = t;
                ans = v;
            }
        }
        return ans;
    }
};
class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        auto lis = [&](const vector<int>& arr) {
            vector<int> g;
            for (int x : arr) {
                auto it = lower_bound(g.begin(), g.end(), x);
                if (it == g.end()) {
                    g.push_back(x);
                } else {
                    *it = x;
                }
            }
            return (int) g.size();
        };

        int ans = 0;
        int mx = ranges::max(nums);
        int m = mx == 0 ? 0 : 32 - __builtin_clz(mx);

        for (int i = 0; i < m; ++i) {
            vector<int> arr;
            ranges::copy_if(nums, back_inserter(arr), [&](int x) {
                return (x >> i) & 1;
            });
            ans = max(ans, lis(arr));
        }

        return ans;
    }
};

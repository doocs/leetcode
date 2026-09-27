class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> d(k, -2);

        auto f = [&](int skip) {
            fill(d.begin(), d.end(), -2);
            d[0] = -1;
            int s = 0, res = 0;
            for (int i = 0; i < n; ++i) {
                int x = i == skip ? -nums[i] : nums[i];
                s = (s + x) % k;
                if (s < 0) {
                    s += k;
                }
                if (d[s] != -2) {
                    res = max(res, i - d[s]);
                } else {
                    d[s] = i;
                }
            }
            return res;
        };

        int ans = f(-1);
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f(i));
        }
        return ans;
    }
};

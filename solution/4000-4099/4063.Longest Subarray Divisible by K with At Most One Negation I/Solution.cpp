class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        auto f = [&]() {
            unordered_map<int, int> d;
            d[0] = -1;
            int s = 0, res = 0;
            for (int i = 0; i < n; ++i) {
                s = (s + nums[i]) % k;
                if (s < 0) {
                    s += k;
                }
                if (d.contains(s)) {
                    res = max(res, i - d[s]);
                } else {
                    d[s] = i;
                }
            }
            return res;
        };

        int ans = f();
        for (int i = 0; i < n; ++i) {
            nums[i] = -nums[i];
            ans = max(ans, f());
            nums[i] = -nums[i];
        }
        return ans;
    }
};

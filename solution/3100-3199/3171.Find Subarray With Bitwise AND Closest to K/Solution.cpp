class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        int mx = *max_element(nums.begin(), nums.end());
        int m = 32 - __builtin_clz(mx);
        int n = nums.size();
        int ans = INT_MAX;
        vector<int> cnt(m);
        for (int i = 0, j = 0, s = -1; j < n; ++j) {
            s &= nums[j];
            ans = min(ans, abs(s - k));
            for (int h = 0; h < m; ++h) {
                if (nums[j] >> h & 1 ^ 1) {
                    ++cnt[h];
                }
            }
            while (i < j && s < k) {
                for (int h = 0; h < m; ++h) {
                    if (nums[i] >> h & 1 ^ 1 && --cnt[h] == 0) {
                        s |= 1 << h;
                    }
                }
                ans = min(ans, abs(s - k));
                ++i;
            }
        }
        return ans;
    }
};
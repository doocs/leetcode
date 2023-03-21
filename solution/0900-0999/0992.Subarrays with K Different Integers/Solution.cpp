class Solution {
public:
    int subarraysWithKDistinct(vector<int>& nums, int k) {
        vector<int> left = f(nums, k);
        vector<int> right = f(nums, k - 1);
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += right[i] - left[i];
        }
        return ans;
    }

    vector<int> f(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> pos(n);
        int cnt[n + 1];
        memset(cnt, 0, sizeof(cnt));
        int s = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++s;
            }
            for (; s > k; ++j) {
                if (--cnt[nums[j]] == 0) {
                    --s;
                }
            }
            pos[i] = j;
        }
        return pos;
    }
};
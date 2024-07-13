class Solution {
public:
    int minIncrementForUnique(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end()) + nums.size();
        int cnt[m];
        memset(cnt, 0, sizeof(cnt));
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
            }
        }
        return ans;
    }
};

class Solution {
public:
    int countKDifference(vector<int>& nums, int k) {
        int ans = 0;
        int cnt[110]{};
        for (int num : nums) {
            if (num >= k) {
                ans += cnt[num - k];
            }
            if (num + k <= 100) {
                ans += cnt[num + k];
            }
            ++cnt[num];
        }
        return ans;
    }
};
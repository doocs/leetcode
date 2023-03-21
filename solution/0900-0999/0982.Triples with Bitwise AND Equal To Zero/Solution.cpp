class Solution {
public:
    int countTriplets(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        int cnt[mx + 1];
        memset(cnt, 0, sizeof cnt);
        for (int& x : nums) {
            for (int& y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int& z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
                }
            }
        }
        return ans;
    }
};
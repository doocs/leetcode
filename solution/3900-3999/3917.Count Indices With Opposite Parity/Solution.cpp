class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int cnt[2] = {0, 0};
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[(nums[i] & 1) ^ 1];
        }
        return ans;
    }
};

class Solution {
public:
    int mostFrequent(vector<int>& nums, int key) {
        int cnt[1001]{};
        int ans = 0, mx = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                if (mx < ++cnt[nums[i + 1]]) {
                    mx = cnt[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
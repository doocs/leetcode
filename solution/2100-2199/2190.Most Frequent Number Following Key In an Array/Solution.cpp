class Solution {
public:
    int mostFrequent(vector<int>& nums, int key) {
        vector<int> cnt(1010);
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
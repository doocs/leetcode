class Solution {
public:
    int resilientSubarray(vector<int>& nums, int k) {
        int ans = 0, cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i] % k;
            if (i > 0 && nums[i - 1] % k == x) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (1LL * x * (cnt - 1) % k == 0) {
                ans = max(ans, cnt);
            }
        }
        return ans;
    }
};

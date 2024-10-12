class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i + 2 < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x < target) {
                    ans += k - j;
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return ans;
    }
};

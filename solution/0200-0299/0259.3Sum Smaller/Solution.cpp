class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if (s >= target)
                    --k;
                else {
                    ans += k - j;
                    ++j;
                }
            }
        }
        return ans;
    }
};
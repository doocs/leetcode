class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int n = nums.size();
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) i = k;
            if (nums[k] == mx) j = k;
        }
        if (i == j) return 0;
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }
};
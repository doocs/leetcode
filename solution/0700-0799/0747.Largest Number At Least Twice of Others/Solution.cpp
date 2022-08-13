class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int mx = 0, mid = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] > mx) {
                mid = mx;
                mx = nums[i];
                ans = i;
            } else if (nums[i] > mid)
                mid = nums[i];
        }
        return mx >= mid * 2 ? ans : -1;
    }
};
class Solution {
public:
    int partitionArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int d = 0, ans = 1;
        for (int i = 1; i < nums.size(); ++i) {
            int a = nums[i - 1], b = nums[i];
            int t = b - a;
            if (d + t <= k)
                d += t;
            else {
                d = 0;
                ++ans;
            }
        }
        return ans;
    }
};
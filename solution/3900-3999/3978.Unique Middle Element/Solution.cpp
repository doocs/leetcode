class Solution {
public:
    bool isMiddleElementUnique(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[n / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
};
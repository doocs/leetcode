class Solution {
public:
    int maxScore(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
};
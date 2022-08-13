class Solution {
public:
    int specialArray(vector<int>& nums) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        for (int x = 0; x <= n; ++x) {
            int idx = lower_bound(nums.begin(), nums.end(), x) - nums.begin();
            int cnt = n - 1 - idx + 1;
            if (cnt == x) return x;
        }
        return -1;
    }
};
class Solution {
public:
    bool canSortArray(vector<int>& nums) {
        int preMx = -300;
        int i = 0, n = nums.size();
        while (i < n) {
            int j = i + 1;
            int cnt = __builtin_popcount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && __builtin_popcount(nums[j]) == cnt) {
                mi = min(mi, nums[j]);
                mx = max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
};
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0, cur = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) ++cnt;
            else cnt = 0;
            if (cnt < 2) nums[cur++] = nums[i];
        }
        return cur;
    }
};
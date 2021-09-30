class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int maxIdx = 0, n = nums.size();
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        for (int i = 0; i < n; ++i) {
            if (i != maxIdx && nums[i] * 2 > nums[maxIdx]) return -1;
        }
        return maxIdx;
    }
};
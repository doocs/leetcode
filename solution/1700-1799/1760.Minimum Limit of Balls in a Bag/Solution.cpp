class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int left = 1, right = *max_element(nums.begin(), nums.end());
        while (left < right) {
            int mid = left + right >> 1;
            long s = 0;
            for (int v : nums) s += (v - 1) / mid;
            if (s <= maxOperations) right = mid;
            else left = mid + 1;
        }
        return left;
    }
};
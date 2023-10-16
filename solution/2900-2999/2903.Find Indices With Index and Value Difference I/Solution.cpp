class Solution {
public:
    vector<int> findIndices(vector<int>& nums, int indexDifference, int valueDifference) {
        int mi = 0, mx = 0;
        for (int i = indexDifference; i < nums.size(); ++i) {
            int j = i - indexDifference;
            if (nums[j] < nums[mi]) {
                mi = j;
            }
            if (nums[j] > nums[mx]) {
                mx = j;
            }
            if (nums[i] - nums[mi] >= valueDifference) {
                return {mi, i};
            }
            if (nums[mx] - nums[i] >= valueDifference) {
                return {mx, i};
            }
        }
        return {-1, -1};
    }
};
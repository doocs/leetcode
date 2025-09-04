class Solution {
public:
    vector<int> sortByAbsoluteValue(vector<int>& nums) {
        sort(nums.begin(), nums.end(), [](int a, int b) {
            return abs(a) < abs(b);
        });
        return nums;
    }
};

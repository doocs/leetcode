class Solution {
public:
    int maximizeGreatness(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = 0;
        for (int x : nums) {
            i += x > nums[i];
        }
        return i;
    }
};
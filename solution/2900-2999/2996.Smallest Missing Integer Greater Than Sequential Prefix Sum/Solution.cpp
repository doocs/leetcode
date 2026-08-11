class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0];
        for (int j = 1; j < nums.size() && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }

        const int m = 51;
        bool st[m] = {};
        for (int x : nums) {
            st[x] = true;
        }

        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
};
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> res;

        sort(nums.begin(), nums.end());
        for (int k = 0; k < nums.size(); k++) {
            int i = k + 1;
            int j = nums.size() - 1;
            if (k > 0 && nums[k] == nums[k - 1]) continue;

            while (i < j) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.push_back(vector<int>{nums[k], nums[i], nums[j]});
                    i++;
                    j--;

                    while (i < j && nums[i] == nums[i - 1]) i++;
                    while (i < j && nums[j] == nums[j + 1]) j--;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        return res;
    }
};
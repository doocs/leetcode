class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n = nums.size();
        if (n < 4) {
            return {};
        }
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1, l = n - 1;
                while (k < l) {
                    if (nums[i] + nums[j] == target - nums[k] - nums[l]) {
                        res.push_back({nums[i], nums[j], nums[k], nums[l]});
                        ++k;
                        --l;
                        while (k < n && nums[k] == nums[k - 1]) ++k;
                        while (l > j && nums[l] == nums[l + 1]) --l;
                    } else if (nums[i] + nums[j] < target - nums[k] - nums[l]) {
                        ++k;
                    } else {
                        --l;
                    }
                }
            }
        }
        return res;
    }
};
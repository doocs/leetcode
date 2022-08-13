class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        if (sum % k != 0) return false;

        int target = sum / k;
        int n = nums.size();
        vector<int> cur(k, 0);

        function<bool(int)> dfs;
        dfs = [&](int i) {
            if (i == n) return true;
            for (int j = 0; j < k; ++j) {
                if (j > 0 && cur[j - 1] == cur[j]) continue;
                cur[j] += nums[i];
                if (cur[j] <= target && dfs(i + 1)) return true;
                cur[j] -= nums[i];
            }
            return false;
        };

        sort(nums.begin(), nums.end(), greater<int>());
        return dfs(0);
    }
};

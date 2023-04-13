class Solution {
public:
    long long countTheNumOfKFreeSubsets(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); ++i) {
            g[nums[i] % k].push_back(nums[i]);
        }
        long long ans = 1;
        for (auto& [_, arr] : g) {
            int m = arr.size();
            long long f[m + 1];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i <= m; ++i) {
                if (arr[i - 1] - arr[i - 2] == k) {
                    f[i] = f[i - 1] + f[i - 2];
                } else {
                    f[i] = f[i - 1] * 2;
                }
            }
            ans *= f[m];
        }
        return ans;
    }
};
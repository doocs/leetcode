class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int n = nums.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];
        }
        vector<int> res;
        for (int i = 0; i < n; ++i) {
            int t = nums[i] * i - presum[i] + presum[n] - presum[i + 1] - nums[i] * (n - i - 1);
            res.push_back(t);
        }
        return res;
    }
};
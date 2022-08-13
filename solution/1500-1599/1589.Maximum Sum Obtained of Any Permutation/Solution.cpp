class Solution {
public:
    int maxSumRangeQuery(vector<int>& nums, vector<vector<int>>& requests) {
        int n = 100010;
        vector<int> delta(n);
        for (auto request : requests) {
            ++delta[request[0]];
            --delta[request[1] + 1];
        }
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
        }
        sort(nums.begin(), nums.end());
        sort(delta.begin(), delta.end());
        long long res = 0;
        for (int i = n - 1, j = nums.size() - 1; i >= 0 && delta[i] > 0; --i, --j) {
            res += (long long)delta[i] * nums[j];
        }
        return (int)(res % 1000000007);
    }
};
class Solution {
public:
    vector<long long> minOperations(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        vector<long long> ans;
        for (auto& x : queries) {
            int i = lower_bound(nums.begin(), nums.end(), x + 1) - nums.begin();
            long long t = s[n] - s[i] - 1LL * (n - i) * x;
            i = lower_bound(nums.begin(), nums.end(), x) - nums.begin();
            t += 1LL * x * i - s[i];
            ans.push_back(t);
        }
        return ans;
    }
};
class Solution {
public:
    int waysToPartition(vector<int>& nums, int k) {
        int n = nums.size();
        long long s[n];
        s[0] = nums[0];
        unordered_map<long long, int> right;
        for (int i = 0; i < n - 1; ++i) {
            right[s[i]]++;
            s[i + 1] = s[i] + nums[i + 1];
        }
        int ans = 0;
        if (s[n - 1] % 2 == 0) {
            ans = right[s[n - 1] / 2];
        }
        unordered_map<long long, int> left;
        for (int i = 0; i < n; ++i) {
            int d = k - nums[i];
            if ((s[n - 1] + d) % 2 == 0) {
                int t = left[(s[n - 1] + d) / 2] + right[(s[n - 1] - d) / 2];
                ans = max(ans, t);
            }
            left[s[i]]++;
            right[s[i]]--;
        }
        return ans;
    }
};
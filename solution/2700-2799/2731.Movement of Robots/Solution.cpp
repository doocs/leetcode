class Solution {
public:
    int sumDistance(vector<int>& nums, string s, int d) {
        int n = nums.size();
        vector<long long> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = 1LL * nums[i] + (s[i] == 'L' ? -d : d);
        }
        sort(arr.begin(), arr.end());
        long long ans = 0;
        long long sum = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + i * arr[i] - sum) % mod;
            sum += arr[i];
        }
        return ans;
    }
};
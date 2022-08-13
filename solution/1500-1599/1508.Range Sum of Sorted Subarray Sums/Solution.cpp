class Solution {
public:
    int rangeSum(vector<int>& nums, int n, int left, int right) {
        const int mod = 1e9 + 7;
        vector<int> arr;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                arr.push_back(s);
            }
        }
        sort(arr.begin(), arr.end());
        int ans = 0;
        for (int i = left - 1; i < right; ++i) ans = (ans + arr[i]) % mod;
        return ans;
    }
};
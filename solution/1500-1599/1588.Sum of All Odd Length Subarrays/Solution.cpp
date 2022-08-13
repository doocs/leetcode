class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        int presum[n + 1];
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + arr[i];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; i + j < n; j += 2) {
                res += presum[i + j + 1] - presum[i];
            }
        }
        return res;
    }
};
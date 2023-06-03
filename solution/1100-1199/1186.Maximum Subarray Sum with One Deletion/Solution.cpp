class Solution {
public:
    int maximumSum(vector<int>& arr) {
        int n = arr.size();
        int left[n];
        int right[n];
        for (int i = 0, s = 0; i < n; ++i) {
            s = max(s, 0) + arr[i];
            left[i] = s;
        }
        for (int i = n - 1, s = 0; ~i; --i) {
            s = max(s, 0) + arr[i];
            right[i] = s;
        }
        int ans = *max_element(left, left + n);
        for (int i = 1; i < n - 1; ++i) {
            ans = max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
};
class Solution {
public:
    int maximumSum(vector<int>& arr) {
        int n = arr.size();
        int left[n];
        int right[n];
        for (int i = 0, t = 0; i < n; ++i) {
            t = max(t, 0) + arr[i];
            left[i] = t;
        }
        for (int i = n - 1, t = 0; ~i; --i) {
            t = max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = *max_element(left, left + n);
        for (int i = 1; i < n - 1; ++i) {
            ans = max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
};
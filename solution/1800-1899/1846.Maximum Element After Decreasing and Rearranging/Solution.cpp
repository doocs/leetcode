class Solution {
public:
    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.size(); ++i) {
            int d = max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = max(ans, arr[i]);
        }
        return ans;
    }
};
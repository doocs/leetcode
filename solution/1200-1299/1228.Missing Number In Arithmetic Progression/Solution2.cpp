class Solution {
public:
    int missingNumber(vector<int>& arr) {
        int n = arr.size();
        int d = (arr[n - 1] - arr[0]) / n;
        for (int i = 1; i < n; ++i)
            if (arr[i] != arr[i - 1] + d) return arr[i - 1] + d;
        return arr[0];
    }
};
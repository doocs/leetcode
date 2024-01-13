class Solution {
public:
    int findLengthOfShortestSubarray(vector<int>& arr) {
        int n = arr.size();
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = min(n - 1 - i, j);
        for (int l = 0; l <= i; ++l) {
            int r = lower_bound(arr.begin() + j, arr.end(), arr[l]) - arr.begin();
            ans = min(ans, r - l - 1);
        }
        return ans;
    }
};
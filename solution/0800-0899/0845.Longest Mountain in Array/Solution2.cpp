class Solution {
public:
    int longestMountain(vector<int>& arr) {
        int n = arr.size();
        int ans = 0;
        for (int l = 0, r = 0; l + 2 < n; l = r) {
            r = l + 1;
            if (arr[l] < arr[r]) {
                while (r + 1 < n && arr[r] < arr[r + 1]) {
                    ++r;
                }
                if (r + 1 < n && arr[r] > arr[r + 1]) {
                    while (r + 1 < n && arr[r] > arr[r + 1]) {
                        ++r;
                    }
                    ans = max(ans, r - l + 1);
                } else {
                    ++r;
                }
            }
        }
        return ans;
    }
};
class Solution {
public:
    int maxSumDistinctTriplet(vector<int>& x, vector<int>& y) {
        int n = x.size();
        vector<array<int, 2>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {x[i], y[i]};
        }
        ranges::sort(arr, [](auto& a, auto& b) {
            return b[1] < a[1];
        });
        int ans = 0;
        unordered_set<int> vis;
        for (int i = 0; i < n; ++i) {
            int a = arr[i][0], b = arr[i][1];
            if (vis.insert(a).second) {
                ans += b;
                if (vis.size() == 3) {
                    return ans;
                }
            }
        }
        return -1;
    }
};

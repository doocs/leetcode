class Solution {
public:
    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<array<int, 4>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {intervals[i][0], intervals[i][1], intervals[i][2], i};
        }
        ranges::sort(arr);
        vector<int> nxt(n);
        for (int i = 0; i < n; ++i) {
            int l = i + 1, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (arr[mid][0] > arr[i][1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            nxt[i] = l;
        }
        vector<vector<long long>> f(n + 1, vector<long long>(5));
        vector<vector<vector<int>>> g(n + 1, vector<vector<int>>(5));
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 1; k < 5; ++k) {
                long long s1 = f[i + 1][k];
                vector<int> a1 = g[i + 1][k];
                long long s2 = f[nxt[i]][k - 1] + arr[i][2];
                vector<int> a2 = g[nxt[i]][k - 1];
                a2.insert(ranges::lower_bound(a2, arr[i][3]), arr[i][3]);
                if (s2 > s1 || (s2 == s1 && a2 < a1)) {
                    f[i][k] = s2;
                    g[i][k] = move(a2);
                } else {
                    f[i][k] = s1;
                    g[i][k] = move(a1);
                }
            }
        }
        return g[0][4];
    }
};

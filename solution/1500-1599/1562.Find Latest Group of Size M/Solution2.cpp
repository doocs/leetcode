class Solution {
public:
    int findLatestStep(vector<int>& arr, int m) {
        int n = arr.size();
        if (m == n) return n;
        vector<int> cnt(n + 2);
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int v = arr[i];
            int l = cnt[v - 1], r = cnt[v + 1];
            if (l == m || r == m) ans = i;
            cnt[v - l] = cnt[v + r] = l + r + 1;
        }
        return ans;
    }
};
class Solution {
public:
    int oddEvenJumps(vector<int>& arr) {
        int n = arr.size();
        map<int, int> d;
        int g[n][2];
        for (int i = n - 1; ~i; --i) {
            auto it = d.lower_bound(arr[i]);
            g[i][1] = it == d.end() ? -1 : it->second;
            it = d.upper_bound(arr[i]);
            g[i][0] = it == d.begin() ? -1 : prev(it)->second;
            d[arr[i]] = i;
        }
        int f[n][2];
        memset(f, 0, sizeof(f));
        f[n - 1][0] = f[n - 1][1] = 1;
        for (int i = n - 2; ~i; --i) {
            for (int k = 0; k < 2; ++k) {
                int j = g[i][k];
                if (j != -1) {
                    f[i][k] = f[j][k ^ 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += f[i][1];
        }
        return ans;
    }
};

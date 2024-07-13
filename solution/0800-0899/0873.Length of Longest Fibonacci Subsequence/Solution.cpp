class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[arr[i]] = i;
            for (int j = 0; j < i; ++j) {
                f[i][j] = 2;
            }
        }

        int ans = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = 1; j < i; ++j) {
                int t = arr[i] - arr[j];
                auto it = d.find(t);
                if (it != d.end() && it->second < j) {
                    int k = it->second;
                    f[i][j] = max(f[i][j], f[j][k] + 1);
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
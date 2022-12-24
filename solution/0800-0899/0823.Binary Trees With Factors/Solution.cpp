class Solution {
public:
    const int mod = 1e9 + 7;

    int numFactoredBinaryTrees(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        unordered_map<int, int> idx;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            idx[arr[i]] = i;
        }
        vector<long> f(n, 1);
        for (int i = 0; i < n; ++i) {
            int a = arr[i];
            for (int j = 0; j < i; ++j) {
                int b = arr[j];
                if (a % b == 0) {
                    int c = a / b;
                    if (idx.count(c)) {
                        int k = idx[c];
                        f[i] = (f[i] + 1l * f[j] * f[k]) % mod;
                    }
                }
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % mod;
        }
        return ans;
    }
};
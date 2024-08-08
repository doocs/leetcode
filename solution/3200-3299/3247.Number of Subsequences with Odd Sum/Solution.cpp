class Solution {
public:
    int subsequenceCount(vector<int>& nums) {
        const int mod = 1e9 + 7;
        vector<int> f(2);
        for (int x : nums) {
            vector<int> g(2);
            if (x % 2 == 1) {
                g[0] = (f[0] + f[1]) % mod;
                g[1] = (f[0] + f[1] + 1) % mod;
            } else {
                g[0] = (f[0] + f[0] + 1) % mod;
                g[1] = (f[1] + f[1]) % mod;
            }
            f = g;
        }
        return f[1];
    }
};

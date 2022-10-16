class Solution {
public:
    const int mod = 1e9 + 7;

    vector<int> productQueries(int n, vector<vector<int>>& queries) {
        vector<int> powers;
        while (n) {
            int x = n & -n;
            powers.emplace_back(x);
            n -= x;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            long long x = 1l;
            for (int j = l; j <= r; ++j) {
                x = (x * powers[j]) % mod;
            }
            ans.emplace_back(x);
        }
        return ans;
    }
};
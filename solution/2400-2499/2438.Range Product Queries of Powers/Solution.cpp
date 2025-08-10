class Solution {
public:
    vector<int> productQueries(int n, vector<vector<int>>& queries) {
        vector<int> powers;
        while (n) {
            int x = n & -n;
            powers.push_back(x);
            n -= x;
        }
        vector<int> ans;
        const int mod = 1e9 + 7;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            long long x = 1;
            for (int j = l; j <= r; ++j) {
                x = x * powers[j] % mod;
            }
            ans.push_back(x);
        }
        return ans;
    }
};

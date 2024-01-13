class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        vector<int> d(26);
        iota(d.begin(), d.end(), 1);
        int m = chars.size();
        for (int i = 0; i < m; ++i) {
            d[chars[i] - 'a'] = vals[i];
        }
        int ans = 0, f = 0;
        for (char& c : s) {
            int v = d[c - 'a'];
            f = max(f, 0) + v;
            ans = max(ans, f);
        }
        return ans;
    }
};
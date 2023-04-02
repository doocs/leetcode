class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        vector<int> d(26, -1);
        int m = chars.size();
        for (int i = 0; i < m; ++i) {
            d[chars[i] - 'a'] = i;
        }
        int ans = 0, tot = 0, mi = 0;
        for (char& c : s) {
            int j = c - 'a';
            int v = d[j] == -1 ? j + 1 : vals[d[j]];
            tot += v;
            ans = max(ans, tot - mi);
            mi = min(mi, tot);
        }
        return ans;
    }
};
class Solution {
public:
    vector<int> platesBetweenCandles(string s, vector<vector<int>>& queries) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '*');
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 0, l = -1; i < n; ++i) {
            if (s[i] == '|') l = i;
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s[i] == '|') r = i;
            right[i] = r;
        }
        vector<int> ans(queries.size());
        for (int k = 0; k < queries.size(); ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) ans[k] = presum[j] - presum[i + 1];
        }
        return ans;
    }
};
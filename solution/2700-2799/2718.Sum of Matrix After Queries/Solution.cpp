class Solution {
public:
    long long matrixSumQueries(int n, vector<vector<int>>& queries) {
        unordered_set<int> row, col;
        reverse(queries.begin(), queries.end());
        long long ans = 0;
        for (auto& q : queries) {
            int t = q[0], i = q[1], v = q[2];
            if (t == 0) {
                if (!row.count(i)) {
                    ans += 1LL * (n - col.size()) * v;
                    row.insert(i);
                }
            } else {
                if (!col.count(i)) {
                    ans += 1LL * (n - row.size()) * v;
                    col.insert(i);
                }
            }
        }
        return ans;
    }
};
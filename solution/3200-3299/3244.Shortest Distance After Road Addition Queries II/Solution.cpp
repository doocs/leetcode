class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<int> nxt(n - 1);
        iota(nxt.begin(), nxt.end(), 1);
        int cnt = n - 1;
        vector<int> ans;
        for (const auto& q : queries) {
            int u = q[0], v = q[1];
            if (nxt[u] && nxt[u] < v) {
                int i = nxt[u];
                while (i < v) {
                    --cnt;
                    int t = nxt[i];
                    nxt[i] = 0;
                    i = t;
                }
                nxt[u] = v;
            }
            ans.push_back(cnt);
        }
        return ans;
    }
};

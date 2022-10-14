using pii = pair<int, int>;

class Solution {
public:
    int minNumberOfSemesters(int n, vector<vector<int>>& relations, int k) {
        vector<int> d(n + 1);
        for (auto& e : relations) {
            d[e[1]] |= 1 << e[0];
        }
        queue<pii> q;
        q.push({0, 0});
        unordered_set<int> vis{{0}};
        while (!q.empty()) {
            auto [cur, t] = q.front();
            q.pop();
            if (cur == (1 << (n + 1)) - 2) {
                return t;
            }
            int nxt = 0;
            for (int i = 1; i <= n; ++i) {
                if ((cur & d[i]) == d[i]) {
                    nxt |= 1 << i;
                }
            }
            nxt ^= cur;
            if (__builtin_popcount(nxt) <= k) {
                if (!vis.count(nxt | cur)) {
                    vis.insert(nxt | cur);
                    q.push({nxt | cur, t + 1});
                }
            } else {
                int x = nxt;
                while (nxt) {
                    if (__builtin_popcount(nxt) == k && !vis.count(nxt | cur)) {
                        vis.insert(nxt | cur);
                        q.push({nxt | cur, t + 1});
                    }
                    nxt = (nxt - 1) & x;
                }
            }
        }
        return 0;
    }
};
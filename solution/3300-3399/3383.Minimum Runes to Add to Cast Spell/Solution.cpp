class Solution {
public:
    vector<int> vis;
    vector<vector<int>> g;
    vector<int> seq;

    int minRunesToAdd(int n, vector<int>& crystals, vector<int>& flowFrom, vector<int>& flowTo) {
        g.resize(n);
        for (int i = 0; i < flowFrom.size(); ++i) {
            g[flowFrom[i]].push_back(flowTo[i]);
        }

        deque<int> q;
        vis.resize(n, 0);
        for (int i : crystals) {
            vis[i] = 1;
            q.push_back(i);
        }
        bfs(q);

        for (int i = 0; i < n; ++i) {
            if (vis[i] == 0) {
                dfs(i);
            }
        }

        int ans = 0;
        for (int i = seq.size() - 1; i >= 0; --i) {
            int a = seq[i];
            if (vis[a] == 2) {
                vis[a] = 1;
                q.clear();
                q.push_back(a);
                bfs(q);
                ++ans;
            }
        }
        return ans;
    }

private:
    void bfs(deque<int>& q) {
        while (!q.empty()) {
            int a = q.front();
            q.pop_front();
            for (int b : g[a]) {
                if (vis[b] == 1) {
                    continue;
                }
                vis[b] = 1;
                q.push_back(b);
            }
        }
    }

    void dfs(int a) {
        vis[a] = 2;
        for (int b : g[a]) {
            if (vis[b] > 0) {
                continue;
            }
            dfs(b);
        }
        seq.push_back(a);
    }
};

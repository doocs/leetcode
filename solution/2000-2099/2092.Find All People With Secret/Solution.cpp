class Solution {
public:
    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {
        vector<bool> vis(n);
        vis[0] = vis[firstPerson] = true;
        sort(meetings.begin(), meetings.end(), [&](const auto& x, const auto& y) {
            return x[2] < y[2];
        });
        for (int i = 0, m = meetings.size(); i < m;) {
            int j = i;
            for (; j + 1 < m && meetings[j + 1][2] == meetings[i][2]; ++j)
                ;
            unordered_map<int, vector<int>> g;
            unordered_set<int> s;
            for (int k = i; k <= j; ++k) {
                int x = meetings[k][0], y = meetings[k][1];
                g[x].push_back(y);
                g[y].push_back(x);
                s.insert(x);
                s.insert(y);
            }
            queue<int> q;
            for (int u : s)
                if (vis[u])
                    q.push(u);
            while (!q.empty()) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.push(v);
                    }
                }
            }
            i = j + 1;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (vis[i])
                ans.push_back(i);
        return ans;
    }
};
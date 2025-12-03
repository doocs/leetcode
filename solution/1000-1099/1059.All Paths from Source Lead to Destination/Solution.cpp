class Solution {
public:
    vector<vector<int>> g;
    vector<int> st;
    int destination;

    bool leadsToDestination(int n, vector<vector<int>>& edges, int source, int destination) {
        this->destination = destination;
        g.assign(n, {});
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        if (!g[destination].empty()) {
            return false;
        }
        st.assign(n, 0);
        return dfs(source);
    }

    bool dfs(int i) {
        if (st[i] != 0) {
            return st[i] == 2;
        }
        if (g[i].empty()) {
            return i == destination;
        }
        st[i] = 1;
        for (int j : g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        st[i] = 2;
        return true;
    }
};

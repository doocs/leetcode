class Solution {
public:
    unordered_map<int, vector<int>> g;
    vector<int> counter;
    vector<int> value;

    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        for (int i = 0; i < nodes; ++i)
            if (parent[i] != -1)
                g[parent[i]].push_back(i);
        counter.resize(nodes, 1);
        this->value = value;
        dfs(0);
        return counter[0];
    }

    void dfs(int u) {
        for (int v : g[u]) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) counter[u] = 0;
    }
};
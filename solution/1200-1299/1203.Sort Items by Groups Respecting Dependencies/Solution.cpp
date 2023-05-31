class Solution {
public:
    vector<int> sortItems(int n, int m, vector<int>& group, vector<vector<int>>& beforeItems) {
        int idx = m;
        vector<vector<int>> groupItems(n + m);
        vector<int> itemDegree(n);
        vector<int> groupDegree(n + m);
        vector<vector<int>> itemGraph(n);
        vector<vector<int>> groupGraph(n + m);
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = idx++;
            }
            groupItems[group[i]].push_back(i);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : beforeItems[i]) {
                if (group[i] == group[j]) {
                    ++itemDegree[i];
                    itemGraph[j].push_back(i);
                } else {
                    ++groupDegree[group[i]];
                    groupGraph[group[j]].push_back(group[i]);
                }
            }
        }
        vector<int> items(n + m);
        iota(items.begin(), items.end(), 0);
        auto topoSort = [](vector<vector<int>>& graph, vector<int>& degree, vector<int>& items) -> vector<int> {
            queue<int> q;
            for (int& i : items) {
                if (degree[i] == 0) {
                    q.push(i);
                }
            }
            vector<int> ans;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ans.push_back(i);
                for (int& j : graph[i]) {
                    if (--degree[j] == 0) {
                        q.push(j);
                    }
                }
            }
            return ans.size() == items.size() ? ans : vector<int>();
        };
        auto groupOrder = topoSort(groupGraph, groupDegree, items);
        if (groupOrder.empty()) {
            return vector<int>();
        }
        vector<int> ans;
        for (int& gi : groupOrder) {
            items = groupItems[gi];
            auto itemOrder = topoSort(itemGraph, itemDegree, items);
            if (items.size() != itemOrder.size()) {
                return vector<int>();
            }
            ans.insert(ans.end(), itemOrder.begin(), itemOrder.end());
        }
        return ans;
    }
};
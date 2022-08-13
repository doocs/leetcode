class Solution {
public:
    int minJumps(vector<int>& arr) {
        unordered_map<int, vector<int>> idx;
        int n = arr.size();
        for (int i = 0; i < n; ++i) idx[arr[i]].push_back(i);
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        unordered_set<int> vis;
        vis.insert(0);
        while (!q.empty()) {
            auto e = q.front();
            q.pop();
            int i = e.first, step = e.second;
            if (i == n - 1) return step;
            int v = arr[i];
            ++step;
            if (idx.count(v)) {
                for (int j : idx[v]) {
                    if (!vis.count(j)) {
                        vis.insert(j);
                        q.emplace(j, step);
                    }
                }
                idx.erase(v);
            }
            if (i + 1 < n && !vis.count(i + 1)) {
                vis.insert(i + 1);
                q.emplace(i + 1, step);
            }
            if (i - 1 >= 0 && !vis.count(i - 1)) {
                vis.insert(i - 1);
                q.emplace(i - 1, step);
            }
        }
        return -1;
    }
};
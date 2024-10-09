class Solution {
public:
    int minJumps(vector<int>& arr) {
        unordered_map<int, vector<int>> g;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            g[arr[i]].push_back(i);
        }
        vector<bool> vis(n);
        queue<int> q{{0}};
        vis[0] = true;
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                if (i == n - 1) {
                    return ans;
                }
                for (int j : g[arr[i]]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
                g[arr[i]].clear();
                for (int j : {i - 1, i + 1}) {
                    if (0 <= j && j < n && !vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
    }
};

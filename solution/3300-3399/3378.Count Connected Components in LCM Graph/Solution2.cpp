class Solution {
private:
    void dfs(int node, vector<vector<int>>& adj, vector<bool>& vis) {
        if (vis[node]) return;
        vis[node] = true;
        for (auto& u : adj[node]) {
            dfs(u, adj, vis);
        }
    }

public:
    int countComponents(vector<int>& nums, int threshold) {
        vector<vector<int>> adj(threshold + 1);
        vector<bool> vis(threshold + 1, false);
        int ans = 0;
        for (auto& num : nums) {
            if (num > threshold) {
                ++ans;
                continue;
            }
            for (int j = 2 * num; j <= threshold; j += num) {
                adj[num].push_back(j);
                adj[j].push_back(num);
            }
        }
        for (auto& num : nums) {
            if (num <= threshold && !vis[num]) {
                dfs(num, adj, vis);
                ++ans;
            }
        }
        return ans;
    }
};

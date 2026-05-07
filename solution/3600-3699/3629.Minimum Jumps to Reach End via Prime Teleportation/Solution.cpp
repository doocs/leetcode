const int mx = 1e6 + 1;
vector<int> factors[mx];

int init = [] {
    for (int i = 2; i < mx; ++i) {
        if (factors[i].empty()) {
            for (int j = i; j < mx; j += i) {
                factors[j].push_back(i);
            }
        }
    }
    return 0;
}();

class Solution {
public:
    int minJumps(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p : factors[x]) {
                g[p].push_back(i);
            }
        }
        int ans = 0;
        vector<bool> vis(n, false);
        vis[0] = true;
        queue<int> q;
        q.push(0);
        while (true) {
            queue<int> nq;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                if (i == n - 1) {
                    return ans;
                }
                vector<int> idx = g[nums[i]];
                idx.push_back(i + 1);
                if (i > 0) {
                    idx.push_back(i - 1);
                }
                for (int j : idx) {
                    if (!vis[j]) {
                        vis[j] = true;
                        nq.push(j);
                    }
                }
                g[nums[i]].clear();
            }
            q = nq;
            ans++;
        }
    }
};

class Solution {
public:
    int minJump(vector<int>& jump) {
        int n = jump.size();
        vector<bool> vis(n + 1);
        queue<int> q {{0}};
        vis[0] = true;
        int ans = 0, mx = 1;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int i = q.front();
                int j = i + jump[i];
                if (j >= n) return ans + 1;
                q.pop();
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
                for (j = mx; j < i; ++j) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
                mx = max(mx, i + 1);
            }
            ++ans;
        }
        return -1;
    }
};
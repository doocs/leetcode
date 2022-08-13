class Solution {
public:
    const int N = 6010;

    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<vector<int>> q;
        q.push({0, 0});
        vector<vector<bool>> vis(N, vector<bool>(2));
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto p = q.front();
                q.pop();
                int i = p[0], dir = p[1];
                if (i == x) return ans;
                vector<vector<int>> nxt;
                nxt.push_back({i + a, 1});
                if (dir) nxt.push_back({i - b, 0});
                for (auto& e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.count(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.push({j, dir});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
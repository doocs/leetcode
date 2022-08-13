class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        using pii = pair<int, int>;
        vector<function<int(int, int)>> ops {
            [](int x, int y) { return x + y; },
            [](int x, int y) { return x - y; },
            [](int x, int y) { return x ^ y; },
        };
        vector<bool> vis(1001, false);
        queue<pii> q;
        q.push({start, 0});
        while (!q.empty()) {
            auto [x, step] = q.front();
            q.pop();
            for (int num : nums) {
                for (auto op : ops) {
                    int nx = op(x, num);
                    if (nx == goal) {
                        return step + 1;
                    }
                    if (nx >= 0 && nx <= 1000 && !vis[nx]) {
                        q.push({nx, step + 1});
                        vis[nx] = true;
                    }
                }
            }
        }
        return -1;
    }
};

/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *   public:
 *     bool canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * };
 */

class Solution {
private:
    const int n = 2010;
    int dirs[5] = {-1, 0, 1, 0, -1};
    string s = "URDL";
    int target;
    unordered_set<int> vis;

public:
    int findShortestPath(GridMaster& master) {
        target = n * n;
        vis.insert(0);
        dfs(0, 0, master);
        if (target == n * n) {
            return -1;
        }
        vis.erase(0);
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        for (int ans = 0; q.size(); ++ans) {
            for (int m = q.size(); m; --m) {
                auto [i, j] = q.front();
                q.pop();
                if (i * n + j == target) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (vis.count(x * n + y)) {
                        vis.erase(x * n + y);
                        q.emplace(x, y);
                    }
                }
            }
        }
        return -1;
    }

    void dfs(int i, int j, GridMaster& master) {
        if (master.isTarget()) {
            target = i * n + j;
        }
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (master.canMove(s[k]) && !vis.count(x * n + y)) {
                vis.insert(x * n + y);
                master.move(s[k]);
                dfs(x, y, master);
                master.move(s[(k + 2) % 4]);
            }
        }
    }
};
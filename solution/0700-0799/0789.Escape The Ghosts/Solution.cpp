class Solution {
public:
    bool escapeGhosts(vector<vector<int>>& ghosts, vector<int>& target) {
        int tx = target[0], ty = target[1];
        for (auto& g : ghosts) {
            int x = g[0], y = g[1];
            if (abs(tx - x) + abs(ty - y) <= abs(tx) + abs(ty)) {
                return false;
            }
        }
        return true;
    }
};
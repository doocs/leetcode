class Solution {
public:
    vector<int> zigzagTraversal(vector<vector<int>>& grid) {
        vector<int> ans;
        bool ok = true;
        for (int i = 0; i < grid.size(); ++i) {
            if (i % 2 != 0) {
                ranges::reverse(grid[i]);
            }
            for (int x : grid[i]) {
                if (ok) {
                    ans.push_back(x);
                }
                ok = !ok;
            }
        }
        return ans;
    }
};

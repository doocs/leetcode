class Solution {
public:
    vector<vector<int>> spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int cnt = rows * cols;
        vector<vector<int>> ans;
        ans.push_back({rStart, cStart});
        if (cnt == 1) return ans;
        for (int k = 1;; k += 2) {
            vector<vector<int>> dirs = {{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}};
            for (auto& dir : dirs) {
                int r = dir[0], c = dir[1], dk = dir[2];
                while (dk-- > 0) {
                    rStart += r;
                    cStart += c;
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        ans.push_back({rStart, cStart});
                        if (ans.size() == cnt) return ans;
                    }
                }
            }
        }
    }
};
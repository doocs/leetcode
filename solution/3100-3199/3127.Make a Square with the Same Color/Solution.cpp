class Solution {
public:
    bool canMakeSquare(vector<vector<char>>& grid) {
        int dirs[5] = {0, 0, 1, 1, 0};
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int cnt1 = 0, cnt2 = 0;
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    cnt1 += grid[x][y] == 'W';
                    cnt2 += grid[x][y] == 'B';
                }
                if (cnt1 != cnt2) {
                    return true;
                }
            }
        }
        return false;
    }
};
class Solution {
public:
    long long maxMatrixSum(vector<vector<int>>& matrix) {
        long long s = 0;
        int mi = 1 << 30, cnt = 0;
        for (const auto& row : matrix) {
            for (int x : row) {
                cnt += x < 0 ? 1 : 0;
                int y = abs(x);
                mi = min(mi, y);
                s += y;
            }
        }
        return cnt % 2 == 0 ? s : s - mi * 2;
    }
};

class Solution {
public:
    int matrixMedian(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int left = 0, right = 1e6 + 1;
        int target = (m * n + 1) >> 1;
        auto count = [&](int x) {
            int cnt = 0;
            for (auto& row : grid) {
                cnt += (upper_bound(row.begin(), row.end(), x) - row.begin());
            }
            return cnt;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
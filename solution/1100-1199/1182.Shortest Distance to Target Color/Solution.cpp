class Solution {
public:
    vector<int> shortestDistanceColor(vector<int>& colors, vector<vector<int>>& queries) {
        int n = colors.size();
        int right[n + 1][3];
        const int inf = 1 << 30;
        fill(right[n], right[n] + 3, inf);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 3; ++j) {
                right[i][j] = right[i + 1][j];
            }
            right[i][colors[i] - 1] = i;
        }
        int left[n + 1][3];
        fill(left[0], left[0] + 3, -inf);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                left[i][j] = left[i - 1][j];
            }
            left[i][colors[i - 1] - 1] = i - 1;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int i = q[0], c = q[1] - 1;
            int d = min(i - left[i + 1][c], right[i][c] - i);
            ans.push_back(d > n ? -1 : d);
        }
        return ans;
    }
};
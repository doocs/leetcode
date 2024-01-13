class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<int> row(m);
        vector<int> col(n);
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int cnt1 = 0, cnt2 = 0;
        for (int v : row) cnt1 += v % 2;
        for (int v : col) cnt2 += v % 2;
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1);
    }
};
class Solution {
public:
    int minMoves(vector<vector<int>>& rooks) {
        sort(rooks.begin(), rooks.end());
        int ans = 0;
        int n = rooks.size();
        for (int i = 0; i < n; ++i) {
            ans += abs(rooks[i][0] - i);
        }
        sort(rooks.begin(), rooks.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        for (int j = 0; j < n; ++j) {
            ans += abs(rooks[j][1] - j);
        }
        return ans;
    }
};
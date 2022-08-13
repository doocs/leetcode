class Solution {
public:
    int maxEqualRowsAfterFlips(vector<vector<int>>& matrix) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& row : matrix) {
            string s = "";
            for (int v : row) {
                if (row[0] == 1) v ^= 1;
                s += to_string(v);
            }
            ++cnt[s];
            ans = max(ans, cnt[s]);
        }
        return ans;
    }
};
class Solution {
public:
    int findBlackPixel(vector<vector<char>>& picture, int target) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        unordered_map<int, vector<int>> cols;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    cols[j].push_back(i);
                }
            }
        }
        vector<vector<bool>> t(m, vector<bool>(m, false));
        for (int i = 0; i < m; ++i) {
            for (int k = i; k < m; ++k) {
                t[i][k] = i == k || all(picture[i], picture[k]);
                t[k][i] = t[i][k];
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == target) {
                for (int j = 0; j < n; ++j) {
                    if (cols[j].size() == target) {
                        bool check = true;
                        for (int k : cols[j]) check = check && t[i][k];
                        if (check) ++res;
                    }
                }
            }
        }
        return res;
    }

    bool all(vector<char>& row1, vector<char>& row2) {
        int n = row1.size();
        for (int j = 0; j < n; ++j)
            if (row1[j] != row2[j]) return false;
        return true;
    }
};
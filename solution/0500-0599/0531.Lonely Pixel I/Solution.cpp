class Solution {
public:
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == 1) {
                for (int j = 0; j < n; ++j) {
                    if (picture[i][j] == 'B' && cols[j] == 1) {
                        ++res;
                        break;
                    }
                }
            }
        }
        return res;
    }
};
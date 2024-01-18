class Solution {
public:
    vector<int> findPeakGrid(vector<vector<int>>& mat) {
        int l = 0, r = mat.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            int j = distance(mat[mid].begin(), max_element(mat[mid].begin(), mat[mid].end()));
            if (mat[mid][j] > mat[mid + 1][j]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int j = distance(mat[l].begin(), max_element(mat[l].begin(), mat[l].end()));
        return {l, j};
    }
};
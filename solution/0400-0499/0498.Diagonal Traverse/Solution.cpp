class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> ans;
        vector<int> t;
        for (int i = 0; i < m + n; ++i)
        {
            int r = i < n ? 0 : i - n + 1;
            int c = i < n ? i : n - 1;
            while (r < m && c >= 0)
            {
                t.push_back(mat[r][c]);
                ++r;
                --c;
            }
            if (i % 2 == 0) reverse(t.begin(), t.end());
            for (int v : t) ans.push_back(v);
            t.clear();
        }
        return ans;
    }
};
class Solution {
public:
    vector<vector<int>> findRLEArray(vector<vector<int>>& encoded1, vector<vector<int>>& encoded2) {
        vector<vector<int>> ans;
        int j = 0;
        for (auto& e : encoded1) {
            int vi = e[0], fi = e[1];
            while (fi) {
                int f = min(fi, encoded2[j][1]);
                int v = vi * encoded2[j][0];
                if (!ans.empty() && ans.back()[0] == v) {
                    ans.back()[1] += f;
                } else {
                    ans.push_back({v, f});
                }
                fi -= f;
                encoded2[j][1] -= f;
                if (encoded2[j][1] == 0) {
                    ++j;
                }
            }
        }
        return ans;
    }
};
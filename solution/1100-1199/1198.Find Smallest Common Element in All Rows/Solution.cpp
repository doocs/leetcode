class Solution {
public:
    int smallestCommonElement(vector<vector<int>>& mat) {
        int cnt[10001]{};
        for (auto& row : mat) {
            for (int x : row) {
                if (++cnt[x] == mat.size()) {
                    return x;
                }
            }
        }
        return -1;
    }
};
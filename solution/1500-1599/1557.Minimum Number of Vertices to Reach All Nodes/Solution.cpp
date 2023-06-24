class Solution {
public:
    vector<int> findSmallestSetOfVertices(int n, vector<vector<int>>& edges) {
        vector<int> cnt(n);
        for (auto& e : edges) {
            ++cnt[e[1]];
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (cnt[i] == 0) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
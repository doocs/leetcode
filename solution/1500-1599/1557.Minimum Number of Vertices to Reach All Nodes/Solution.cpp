class Solution {
public:
    vector<int> findSmallestSetOfVertices(int n, vector<vector<int>>& edges) {
        unordered_set<int> s;
        for (auto& e : edges) s.insert(e[1]);
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!s.count(i)) ans.push_back(i);
        }
        return ans;
    }
};
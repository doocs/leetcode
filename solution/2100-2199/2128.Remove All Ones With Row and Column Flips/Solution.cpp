class Solution {
public:
    bool removeOnes(vector<vector<int>>& grid) {
        unordered_set<string> s;
        for (auto& row : grid) {
            string t;
            for (int x : row) {
                t.push_back('0' + (row[0] == 0 ? x : x ^ 1));
            }
            s.insert(t);
        }
        return s.size() == 1;
    }
};
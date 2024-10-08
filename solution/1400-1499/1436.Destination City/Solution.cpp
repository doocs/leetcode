class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_set<string> s;
        for (auto& p : paths) {
            s.insert(p[0]);
        }
        for (int i = 0;; ++i) {
            auto b = paths[i][1];
            if (!s.contains(b)) {
                return b;
            }
        }
    }
};

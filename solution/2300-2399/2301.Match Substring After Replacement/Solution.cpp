class Solution {
public:
    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {
        unordered_map<char, unordered_set<char>> d;
        for (auto& m : mappings) d[m[0]].insert(m[1]);
        int n = s.size(), k = sub.size();
        for (int i = 0; i <= n - k; ++i) {
            bool flag = true;
            for (int j = 0; j < k; ++j) {
                char a = s[i + j], b = sub[j];
                if (a == b || d[b].count(a)) continue;
                flag = false;
                break;
            }
            if (flag) return true;
        }
        return false;
    }
};
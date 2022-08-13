class Solution {
public:
    int countDistinct(string s) {
        unordered_set<string_view> ss;
        int n = s.size();
        string_view t, v = s;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                t = v.substr(i, j - i);
                ss.insert(t);
            }
        }
        return ss.size();
    }
};
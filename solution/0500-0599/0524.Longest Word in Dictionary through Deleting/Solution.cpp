class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        string ans = "";
        auto check = [&](const string& s, const string& t) {
            int m = s.size(), n = t.size();
            int i = 0;
            for (int j = 0; i < m && j < n; ++j) {
                if (s[i] == t[j]) {
                    ++i;
                }
            }
            return i == m;
        };
        for (auto& t : dictionary) {
            int a = ans.size(), b = t.size();
            if (check(t, s) && (a < b || (a == b && ans > t))) {
                ans = t;
            }
        }
        return ans;
    }
};
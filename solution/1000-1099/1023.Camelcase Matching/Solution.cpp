class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        vector<bool> ans;
        auto check = [](string& s, string& t) {
            int m = s.size(), n = t.size();
            int i = 0, j = 0;
            for (; j < n; ++i, ++j) {
                while (i < m && s[i] != t[j] && islower(s[i])) {
                    ++i;
                }
                if (i == m || s[i] != t[j]) {
                    return false;
                }
            }
            while (i < m && islower(s[i])) {
                ++i;
            }
            return i == m;
        };
        for (auto& q : queries) {
            ans.push_back(check(q, pattern));
        }
        return ans;
    }
};
class Solution {
public:
    string removeSubstring(string s, int k) {
        vector<pair<char, int>> stk;
        for (char c : s) {
            if (!stk.empty() && stk.back().first == c) {
                stk.back().second += 1;
            } else {
                stk.emplace_back(c, 1);
            }
            if (c == ')' && stk.size() > 1) {
                auto& top = stk.back();
                auto& prev = stk[stk.size() - 2];
                if (top.second == k && prev.second >= k) {
                    stk.pop_back();
                    prev.second -= k;
                    if (prev.second == 0) {
                        stk.pop_back();
                    }
                }
            }
        }
        string res;
        for (auto& p : stk) {
            res.append(p.second, p.first);
        }
        return res;
    }
};

class Solution {
public:
    string removeDuplicates(string s, int k) {
        vector<pair<char, int>> stk;
        for (char& c : s) {
            if (stk.size() && stk.back().first == c) {
                stk.back().second = (stk.back().second + 1) % k;
                if (stk.back().second == 0) {
                    stk.pop_back();
                }
            } else {
                stk.push_back({c, 1});
            }
        }
        string ans;
        for (auto [c, v] : stk) {
            ans += string(v, c);
        }
        return ans;
    }
};
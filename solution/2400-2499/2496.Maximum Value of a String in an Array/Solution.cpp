class Solution {
public:
    int maximumValue(vector<string>& strs) {
        auto f = [](string& s) {
            int n = s.size(), m = 0;
            for (char& c : s) {
                if (!isdigit(c)) return n;
                m = m * 10 + (c - '0');
            }
            return m;
        };
        int ans = 0;
        for (auto& s : strs) ans = max(ans, f(s));
        return ans;
    }
};
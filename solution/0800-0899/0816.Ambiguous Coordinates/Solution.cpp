class Solution {
public:
    vector<string> ambiguousCoordinates(string s) {
        int n = s.size();
        vector<string> ans;
        auto f = [&](int i, int j) {
            vector<string> res;
            for (int k = 1; k <= j - i; ++k) {
                string l = s.substr(i, k);
                string r = s.substr(i + k, j - i - k);
                bool ok = (l == "0" || l[0] != '0') && r.back() != '0';
                if (ok) {
                    res.push_back(l + (k < j - i ? "." : "") + r);
                }
            }
            return res;
        };
        for (int i = 2; i < n - 1; ++i) {
            for (auto& x : f(1, i)) {
                for (auto& y : f(i, n - 1)) {
                    ans.emplace_back("(" + x + ", " + y + ")");
                }
            }
        }
        return ans;
    }
};
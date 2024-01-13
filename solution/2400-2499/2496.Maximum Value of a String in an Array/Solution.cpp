class Solution {
public:
    int maximumValue(vector<string>& strs) {
        auto f = [](string& s) {
            int x = 0;
            for (char& c : s) {
                if (!isdigit(c)) {
                    return (int) s.size();
                }
                x = x * 10 + c - '0';
            }
            return x;
        };
        int ans = 0;
        for (auto& s : strs) {
            ans = max(ans, f(s));
        }
        return ans;
    }
};
class Solution {
public:
    int maxPower(string s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (i == 0 || s[i] == s[i - 1])
                ++t;
            else
                t = 1;
            ans = max(ans, t);
        }
        return ans;
    }
};
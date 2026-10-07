class Solution {
public:
    int minRotations(string s) {
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < s.size(); ++i) {
            int cur = s[i] - '0';
            int diff = abs(cur - pre);
            ans += min(diff, 10 - diff);
            pre = cur;
        }
        return ans;
    }
};
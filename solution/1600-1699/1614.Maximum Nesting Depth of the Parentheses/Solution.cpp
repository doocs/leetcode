class Solution {
public:
    int maxDepth(string s) {
        int n = 0, ans = 0;
        for (char c : s) {
            if (c == '(')
                ans = max(ans, ++n);
            else if (c == ')')
                --n;
        }
        return ans;
    }
};
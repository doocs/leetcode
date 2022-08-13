class Solution {
public:
    int balancedStringSplit(string s) {
        int ans = 0, l = 0;
        for (char c : s) {
            if (c == 'L')
                ++l;
            else
                --l;
            if (l == 0) ++ans;
        }
        return ans;
    }
};
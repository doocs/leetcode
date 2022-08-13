class Solution {
public:
    int countAsterisks(string s) {
        int ans = 0, t = 0;
        for (char& c : s) {
            if (c == '|')
                t ^= 1;
            else if (c == '*')
                ans += t == 0;
        }
        return ans;
    }
};
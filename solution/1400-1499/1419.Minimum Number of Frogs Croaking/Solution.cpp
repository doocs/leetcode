class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char ch : croakOfFrogs) {
            if (ch == 'c') {
                ++c;
                if (k > 0)
                    --k;
                else
                    ++ans;
            } else if (ch == 'r') {
                ++r;
                --c;
            } else if (ch == 'o') {
                ++o;
                --r;
            } else if (ch == 'a') {
                ++a;
                --o;
            } else {
                ++k;
                --a;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) return -1;
        }
        return c == 0 && r == 0 && o == 0 && a == 0 ? ans : -1;
    }
};
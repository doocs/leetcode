class Solution {
public:
    int minMovesToMakePalindrome(string s) {
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = n - 1; i < j; ++i) {
            bool even = false;
            for (int k = j; k != i; --k) {
                if (s[i] == s[k]) {
                    even = true;
                    for (; k < j; ++k) {
                        swap(s[k], s[k + 1]);
                        ++ans;
                    }
                    --j;
                    break;
                }
            }
            if (!even) ans += n / 2 - i;
        }
        return ans;
    }
};
class Solution {
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        int ans = 0;
        char[] cs = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; ++i) {
            boolean even = false;
            for (int k = j; k != i; --k) {
                if (cs[i] == cs[k]) {
                    even = true;
                    for (; k < j; ++k) {
                        char t = cs[k];
                        cs[k] = cs[k + 1];
                        cs[k + 1] = t;
                        ++ans;
                    }
                    --j;
                    break;
                }
            }
            if (!even) {
                ans += n / 2 - i;
            }
        }
        return ans;
    }
}
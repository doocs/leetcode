class Solution {
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String str = "IVXLCDM";
        int[] num = {1, 5, 10, 50, 100, 500, 1000};
        int ans = 0;
        int i = 0;
        int pre = 999, cur = 0;
        while (i < s.length()) {
            cur = str.indexOf(s.charAt(i++));
            if (pre < cur) {
                ans = ans + num[cur] - 2 * num[pre];
            } else {
                ans += num[cur];
            }
            pre = cur;
        }
        return ans;
    }
}
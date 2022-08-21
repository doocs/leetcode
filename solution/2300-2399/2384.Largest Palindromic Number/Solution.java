class Solution {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) {
            ++cnt[c - '0'];
        }
        String mid = "";
        for (int i = 9; i >= 0; --i) {
            if (cnt[i] % 2 == 1) {
                mid += i;
                --cnt[i];
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            if (cnt[i] > 0) {
                cnt[i] >>= 1;
                sb.append(("" + i).repeat(cnt[i]));
            }
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        String t = sb.toString();
        String ans = sb.reverse().toString() + mid + t;
        return "".equals(ans) ? "0" : ans;
    }
}
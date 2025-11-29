class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder t = new StringBuilder();
        String ch = "";

        for (char c = 'a'; c <= 'z'; c++) {
            int idx = c - 'a';
            int v = cnt[idx] / 2;
            if (v > 0) {
                t.append(String.valueOf(c).repeat(v));
            }
            cnt[idx] -= v * 2;
            if (cnt[idx] == 1) {
                ch = String.valueOf(c);
            }
        }

        String ans = t.toString();
        ans = ans + ch + new StringBuilder(ans).reverse();
        return ans;
    }
}

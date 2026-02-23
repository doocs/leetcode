class Solution {
    public String maximumXor(String s, String t) {
        int[] cnt = new int[2];
        for (char c : t.toCharArray()) {
            cnt[c - '0']++;
        }

        char[] ans = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - '0';
            if (cnt[x ^ 1] > 0) {
                cnt[x ^ 1]--;
                ans[i] = '1';
            } else {
                cnt[x]--;
                ans[i] = '0';
            }
        }

        return new String(ans);
    }
}

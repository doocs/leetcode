class Solution {
    public String smallestSubsequence(String text) {
        int[] cnt = new int[26];
        for (char c : text.toCharArray()) {
            ++cnt[c - 'a'];
        }
        boolean[] vis = new boolean[26];
        char[] cs = new char[text.length()];
        int top = -1;
        for (char c : text.toCharArray()) {
            --cnt[c - 'a'];
            if (!vis[c - 'a']) {
                while (top >= 0 && c < cs[top] && cnt[cs[top] - 'a'] > 0) {
                    vis[cs[top--] - 'a'] = false;
                }
                cs[++top] = c;
                vis[c - 'a'] = true;
            }
        }
        return String.valueOf(cs, 0, top + 1);
    }
}

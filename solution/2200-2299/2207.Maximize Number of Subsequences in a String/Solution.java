class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        int[] cnt = new int[26];
        char a = pattern.charAt(0);
        char b = pattern.charAt(1);
        long ans = 0;
        for (char c : text.toCharArray()) {
            if (c == b) {
                ans += cnt[a - 'a'];
            }
            cnt[c - 'a']++;
        }
        ans += Math.max(cnt[a - 'a'], cnt[b - 'a']);
        return ans;
    }
}
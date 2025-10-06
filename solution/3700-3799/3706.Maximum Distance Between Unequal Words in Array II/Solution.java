class Solution {
    public int maxDistance(String[] words) {
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!words[i].equals(words[0])) {
                ans = Math.max(ans, i + 1);
            }
            if (!words[i].equals(words[n - 1])) {
                ans = Math.max(ans, n - i);
            }
        }
        return ans;
    }
}

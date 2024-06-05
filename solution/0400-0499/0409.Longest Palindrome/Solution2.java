class Solution {
    public int longestPalindrome(String s) {
        int[] odd = new int[128];
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            odd[s.charAt(i)] ^= 1;
            cnt += odd[s.charAt(i)] == 1 ? 1 : -1;
        }
        return cnt > 0 ? n - cnt + 1 : n;
    }
}

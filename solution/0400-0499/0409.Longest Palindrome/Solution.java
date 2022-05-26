class Solution {
    public int longestPalindrome(String s) {
        int[] res = new int[128];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            res[s.charAt(i)]++;
        }
        int oddCnt = 0;
        for (int e : res) {
            oddCnt += (e % 2);
        }
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
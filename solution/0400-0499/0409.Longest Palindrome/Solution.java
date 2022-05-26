class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            ++counter[c];
        }
        int oddCnt = 0;
        for (int e : counter) {
            oddCnt += (e % 2);
        }
        int n = s.length();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int oddCnt = 0;
        for (int cnt : counter) {
            if (cnt % 2 == 1) {
                ++oddCnt;
            }
        }
        return oddCnt < 2;
    }
}
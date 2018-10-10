class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0;
        int y = x;
        while (y != 0) {
            res = res * 10 + y % 10;
            y /= 10;
        }
        return res == x;
    }
}
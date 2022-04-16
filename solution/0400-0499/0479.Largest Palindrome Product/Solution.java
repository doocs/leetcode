class Solution {
    public int largestPalindrome(int n) {
        int mx = (int) Math.pow(10, n) - 1;
        for (int a = mx; a > mx / 10; --a) {
            int b = a;
            long x = a;
            while (b != 0) {
                x = x * 10 + b % 10;
                b /= 10;
            }
            for (long t = mx; t * t >= x; --t) {
                if (x % t == 0) {
                    return (int) (x % 1337);
                }
            }
        }
        return 9;
    }
}
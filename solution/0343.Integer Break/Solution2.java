/**
 * O(1) ==》 time complexity
 * O(1) ==》space complexity
 */
class Solution {
    public int integerBreak(int n) {
        if (n <= 3) return n-1;

        int a = n/3, b = n%3;
        if (b == 0) return pow(3, a);
        if (b == 1) return pow(3, a-1) << 2;
        return pow(3, a) << 1;
    }

    private int pow(int a, int b) {
        if (b == 0) return 1;
        return a * pow(a,  b-1);
    }
}
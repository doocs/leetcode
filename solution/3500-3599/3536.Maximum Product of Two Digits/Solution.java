class Solution {
    public int maxProduct(int n) {
        int a = 0, b = 0;
        for (; n > 0; n /= 10) {
            int x = n % 10;
            if (a < x) {
                b = a;
                a = x;
            } else if (b < x) {
                b = x;
            }
        }
        return a * b;
    }
}
class Solution {
    public int sumOfDigits(int[] nums) {
        int x = 100;
        for (int v : nums) {
            x = Math.min(x, v);
        }
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
}
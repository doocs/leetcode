class Solution {
    public int findGCD(int[] nums) {
        int a = 1, b = 1000;
        for (int num : nums) {
            a = Math.max(a, num);
            b = Math.min(b, num);
        }
        return gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
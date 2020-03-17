class Solution {
    // fast slow point
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        } while (slow != fast);
        return fast == 1 ? true : false;
    }

    private int squareSum(int m) {
        int squaresum = 0;
        while (m != 0) {
            squaresum += (m%10) * (m%10);
            m /= 10;
        }
        return squaresum;
    }
}
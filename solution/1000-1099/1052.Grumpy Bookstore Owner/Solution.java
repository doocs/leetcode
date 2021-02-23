class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int s = 0, t = 0;
        for (int i = 0, win = 0, n = customers.length; i < n; ++i) {
            if (grumpy[i] == 0) {
                s += customers[i];
            } else {
                win += customers[i];
            }
            if (i >= X && grumpy[i - X] == 1) {
                win -= customers[i - X];
            }
            t = Math.max(t, win);
        }
        return s + t;
    }
}
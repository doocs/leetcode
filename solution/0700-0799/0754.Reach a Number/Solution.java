class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int s = 0, k = 0;
        while (true) {
            if (s >= target && (s - target) % 2 == 0) {
                return k;
            }
            ++k;
            s += k;
        }
    }
}
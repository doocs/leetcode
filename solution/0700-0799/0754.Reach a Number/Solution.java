class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int s = 0;
        int k = 0;
        while (true) {
            if (s >= target && (s - target) % 2 == 0) {
                break;
            }
            ++k;
            s += k;
        }
        return k;
    }
}
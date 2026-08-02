class Solution {
    private int[] monsters;
    private long[] d;

    public long minInitialStrength(int[] monsters, int[][] boosts) {
        this.monsters = monsters;
        int n = monsters.length;
        d = new long[n + 1];
        for (int[] b : boosts) {
            d[b[0]] += b[2];
            d[b[1] + 1] -= b[2];
        }

        long left = 0, right = (long) 1e15;
        while (left < right) {
            long mid = (left + right) >>> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(long v) {
        long bonus = 0;
        for (int i = 0; i < monsters.length; i++) {
            bonus += d[i];
            if (v + bonus < monsters[i]) {
                return false;
            }
            v -= monsters[i];
            if (v < 0) {
                v = 0;
            }
        }
        return true;
    }
}
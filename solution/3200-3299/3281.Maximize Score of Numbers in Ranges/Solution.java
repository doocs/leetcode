class Solution {
    private int[] start;
    private int d;

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        this.start = start;
        this.d = d;
        int n = start.length;
        int l = 0, r = start[n - 1] + d - start[0];
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int mi) {
        long last = Long.MIN_VALUE;
        for (int st : start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    }
}

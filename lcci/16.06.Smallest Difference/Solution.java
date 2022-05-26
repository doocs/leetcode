class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int m = a.length, n = b.length;
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < m && j < n) {
            if (a[i] == b[j]) return 0;
            res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] > b[j]) ++j;
            else ++i;
        }
        return (int) res;
    }
}
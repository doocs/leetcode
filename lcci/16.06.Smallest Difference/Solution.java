class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] > b[j]) {
                ++j;
            } else {
                ++i;
            }
        }
        return (int) res;
    }
}
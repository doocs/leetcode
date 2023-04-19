class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int x : citations) {
            ++cnt[Math.min(x, n)];
        }
        for (int h = n, s = 0; ; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
}
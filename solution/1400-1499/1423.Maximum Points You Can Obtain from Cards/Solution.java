class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + cardPoints[i];
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int j = i + (n - k) - 1;
            if (j < n) {
                mi = Math.min(mi, s[j + 1] - s[i]);
            }
        }
        return s[n] - mi;
    }
}
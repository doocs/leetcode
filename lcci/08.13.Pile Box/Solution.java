class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = box.length;
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += box[i][2];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
class Solution {
    public int maxHeight(int[][] cuboids) {
        for (var c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids,
            (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        int n = cuboids.length;
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += cuboids[i][2];
        }
        return Arrays.stream(f).max().getAsInt();
    }
}
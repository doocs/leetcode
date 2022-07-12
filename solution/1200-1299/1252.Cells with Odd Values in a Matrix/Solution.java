class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int ans = 0;
        for (int i : row) {
            for (int j : col) {
                ans += (i + j) % 2;
            }
        }
        return ans;
    }
}
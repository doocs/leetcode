class Solution {
    private String[] plate;
    private int num;
    private int m;
    private int n;
    private final int[] dirs = {0, 1, 0, -1, 0};

    public int[][] ballGame(int num, String[] plate) {
        this.num = num;
        this.plate = plate;
        this.m = plate.length;
        this.n = plate[0].length();
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i) {
            if (plate[i].charAt(0) == '.' && check(i, 0, 0)) {
                ans.add(new int[]{i, 0});
            }
            if (plate[i].charAt(n - 1) == '.' && check(i, n - 1, 2)) {
                ans.add(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; ++j) {
            if (plate[0].charAt(j) == '.' && check(0, j, 1)) {
                ans.add(new int[]{0, j});
            }
            if (plate[m - 1].charAt(j) == '.' && check(m - 1, j, 3)) {
                ans.add(new int[]{m - 1, j});
            }
        }
        return ans.toArray(new int[0][]);
    }

    private boolean check(int i, int j, int d) {
        int k = num;
        while (plate[i].charAt(j) != 'O') {
            if (k == 0) {
                return false;
            }
            if (plate[i].charAt(j) == 'W') {
                d = (d + 3) % 4;
            } else if (plate[i].charAt(j) == 'E') {
                d = (d + 1) % 4;
            }
            i = i + dirs[d];
            j = j + dirs[d + 1];
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return false;
            }
            --k;
        }
        return true;
    }
}
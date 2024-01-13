class Solution {
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        int n = cookbooks.length;
        int ans = -1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int a = 0, b = 0;
            int[] cnt = new int[5];
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    int x = attribute[i][0];
                    int y = attribute[i][1];
                    a += x;
                    b += y;
                    for (int j = 0; j < cookbooks[i].length; ++j) {
                        cnt[j] += cookbooks[i][j];
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < 5 && ok; ++i) {
                ok = cnt[i] <= materials[i];
            }
            if (b >= limit && ans < a && ok) {
                ans = a;
            }
        }
        return ans;
    }
}
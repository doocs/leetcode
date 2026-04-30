class Solution {
    private boolean[] key;
    private long[][] f;
    private String s;

    public long countGoodIntegersOnPath(long l, long r, String directions) {
        key = new boolean[16];
        int row = 0, col = 0;
        key[0] = true;
        for (char c : directions.toCharArray()) {
            if (c == 'D') {
                row++;
            } else {
                col++;
            }
            key[row * 4 + col] = true;
        }

        return calc(r) - calc(l - 1);
    }

    private long dfs(int pos, int last, boolean lim) {
        if (pos == 16) {
            return 1;
        }
        if (!lim && f[pos][last] != -1) {
            return f[pos][last];
        }

        long res = 0;
        int start = key[pos] ? last : 0;
        int end = lim ? (s.charAt(pos) - '0') : 9;

        for (int i = start; i <= end; i++) {
            res += dfs(pos + 1, key[pos] ? i : last, lim && (i == end));
        }

        if (!lim) {
            f[pos][last] = res;
        }
        return res;
    }

    private long calc(long x) {
        if (x < 0) {
            return 0;
        }
        String t = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16 - t.length(); i++) {
            sb.append('0');
        }
        s = sb.append(t).toString();
        f = new long[16][10];
        for (long[] row : f) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true);
    }
}

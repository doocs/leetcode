class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String a = words[i];
            for (int j = 0; j < n; ++j) {
                String b = words[j];
                if (i != j) {
                    for (int k = Math.min(a.length(), b.length()); k > 0; --k) {
                        if (a.substring(a.length() - k).equals(b.substring(0, k))) {
                            g[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        int[][] dp = new int[1 << n][n];
        int[][] p = new int[1 << n][n];
        for (int i = 0; i < 1 << n; ++i) {
            Arrays.fill(p[i], -1);
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    int pi = i ^ (1 << j);
                    for (int k = 0; k < n; ++k) {
                        if (((pi >> k) & 1) == 1) {
                            int v = dp[pi][k] + g[k][j];
                            if (v > dp[i][j]) {
                                dp[i][j] = v;
                                p[i][j] = k;
                            }
                        }
                    }
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[(1 << n) - 1][i] > dp[(1 << n) - 1][j]) {
                j = i;
            }
        }
        List<Integer> arr = new ArrayList<>();
        arr.add(j);
        for (int i = (1 << n) - 1; p[i][j] != -1;) {
            int k = i;
            i ^= (1 << j);
            j = p[k][j];
            arr.add(j);
        }
        Set<Integer> vis = new HashSet<>(arr);
        for (int i = 0; i < n; ++i) {
            if (!vis.contains(i)) {
                arr.add(i);
            }
        }
        Collections.reverse(arr);
        StringBuilder ans = new StringBuilder(words[arr.get(0)]);
        for (int i = 1; i < n; ++i) {
            int k = g[arr.get(i - 1)][arr.get(i)];
            ans.append(words[arr.get(i)].substring(k));
        }
        return ans.toString();
    }
}
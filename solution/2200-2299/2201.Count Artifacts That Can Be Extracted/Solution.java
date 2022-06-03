class Solution {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        Set<Integer> s = new HashSet<>();
        for (int[] d : dig) {
            s.add(d[0] * n + d[1]);
        }
        int ans = 0;
        for (int[] a : artifacts) {
            if (check(a, s, n)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int[] a, Set<Integer> s, int n) {
        int r1 = a[0], c1 = a[1], r2 = a[2], c2 = a[3];
        for (int i = r1; i <= r2; ++i) {
            for (int j = c1; j <= c2; ++j) {
                if (!s.contains(i * n + j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
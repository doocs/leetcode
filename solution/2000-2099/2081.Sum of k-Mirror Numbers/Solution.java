class Solution {
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1;; l++) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                int j = (l % 2 == 0) ? i : i / 10;
                while (j > 0) {
                    v = v * 10 + j % 10;
                    j /= 10;
                }
                if (check(v, k)) {
                    ans += v;
                    n--;
                    if (n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

    private boolean check(long x, int k) {
        List<Integer> s = new ArrayList<>();
        while (x > 0) {
            s.add((int) (x % k));
            x /= k;
        }
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (!s.get(i).equals(s.get(j))) {
                return false;
            }
        }
        return true;
    }
}

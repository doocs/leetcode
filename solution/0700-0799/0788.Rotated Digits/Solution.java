class Solution {
    private static final Map<Integer, Integer> d = new HashMap<>();
    static {
        d.put(0, 0);
        d.put(1, 1);
        d.put(8, 8);
        d.put(2, 5);
        d.put(5, 2);
        d.put(6, 9);
        d.put(9, 6);
    }

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (!d.containsKey(v)) {
                return false;
            }
            y = d.get(v) * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
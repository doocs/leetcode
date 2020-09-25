class Solution {
    public int numWays(String s) {
        char[] chars = s.toCharArray();
        List<Long> p = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                p.add((long) i);
            }
        }
        int l = p.size();
        if (l % 3 != 0) {
            return 0;
        }

        int MOD = (int) (1e9 + 7);
        if (l == 0) {
            return (int) (((long) (s.length() - 1) * (s.length() - 2) / 2) % MOD);
        }

        // 每 n/3 的地方为分界线
        return (int) ((p.get(l / 3) - p.get(l / 3 - 1)) * (p.get(2 * l / 3) - p.get(2 * l / 3 - 1))
                % MOD);
    }
}
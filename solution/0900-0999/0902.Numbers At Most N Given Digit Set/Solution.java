class Solution {
    private Set<Integer> nums = new HashSet<>();
    private char[] s;
    private Integer[] f;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length];
        for (var x : digits) {
            nums.add(Integer.parseInt(x));
        }
        return dfs(0, true, true);
    }

    private int dfs(int i, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i] != null) {
            return f[i];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 0 && lead) {
                ans += dfs(i + 1, true, limit && j == up);
            } else if (nums.contains(j)) {
                ans += dfs(i + 1, false, limit && j == up);
            }
        }
        if (!lead && !limit) {
            f[i] = ans;
        }
        return ans;
    }
}
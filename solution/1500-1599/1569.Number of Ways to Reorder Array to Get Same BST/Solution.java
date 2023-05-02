class Solution {
    private int[][] c;
    private final int mod = (int) 1e9 + 7;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new int[n][n];
        c[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        return (dfs(list) - 1 + mod) % mod;
    }

    private int dfs(List<Integer> nums) {
        if (nums.size() < 2) {
            return 1;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int x : nums) {
            if (x < nums.get(0)) {
                left.add(x);
            } else if (x > nums.get(0)) {
                right.add(x);
            }
        }
        int m = left.size(), n = right.size();
        int a = dfs(left), b = dfs(right);
        return (int) ((long) a * b % mod * c[m + n][n] % mod);
    }
}
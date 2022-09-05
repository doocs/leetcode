class Solution {
    int mod = (int) 1e9 + 7;

    public int numOfWays(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        return dfs(Arrays.stream(nums).boxed().collect(Collectors.toList()), calc(nums.length)) - 1;
    }

    private int dfs(List<Integer> list, int[][] c) {
        if (list.isEmpty()) {
            return 1;
        }
        List<Integer> left
            = list.stream().filter(n -> n < list.get(0)).collect(Collectors.toList());
        List<Integer> right
            = list.stream().filter(n -> n > list.get(0)).collect(Collectors.toList());
        return (int) ((long) c[list.size() - 1][left.size()] * dfs(left, c) % mod * dfs(right, c)
            % mod);
    }

    private int[][] calc(int n) {
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
                }
            }
        }
        return c;
    }
}
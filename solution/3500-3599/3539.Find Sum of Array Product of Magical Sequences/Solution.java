class Solution {
  public int magicalSum(int m, int k, int[] nums) {
    int[][] comb = getComb(m, m);
    Integer[][][][] mem = new Integer[m + 1][k + 1][nums.length + 1][m + 1];
    return dp(m, k, 0, 0, nums, mem, comb);
  }

  private static final int MOD = 1_000_000_007;

  private int dp(int m, int k, int i, int carry, int[] nums, Integer[][][][] mem, int[][] comb) {
    if (m < 0 || k < 0 || (m + Integer.bitCount(carry) < k))
      return 0;
    if (m == 0)
      return k == Integer.bitCount(carry) ? 1 : 0;
    if (i == nums.length)
      return 0;
    if (mem[m][k][i][carry] != null)
      return mem[m][k][i][carry];
    int res = 0;
    for (int count = 0; count <= m; count++) {
      final long contribution = comb[m][count] * modPow(nums[i], count) % MOD;
      final int newCarry = carry + count;
      res = (int) ((res +
                    (long) dp(m - count, k - (newCarry % 2), i + 1, newCarry / 2, nums, mem, comb) *
                        contribution) %
                   MOD);
    }
    return mem[m][k][i][carry] = res;
  }

  // C(n, k) = C(n - 1, k) + C(n - 1, k - 1)
  private int[][] getComb(int n, int k) {
    int[][] comb = new int[n + 1][k + 1];
    for (int i = 0; i <= n; i++)
      comb[i][0] = 1;
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= k; j++)
        comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
    return comb;
  }

  private long modPow(long x, long n) {
    if (n == 0)
      return 1;
    if (n % 2 == 1)
      return x * modPow(x % MOD, n - 1) % MOD;
    return modPow(x * x % MOD, n / 2) % MOD;
  }
}

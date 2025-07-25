class Solution {
  public int minCostToEqualizeArray(int[] A, int c1, int c2) {
    int ma = A[0], mi = A[0], n = A.length, mod = 1000000007;
    long total = 0;
    for (int a : A) {
      mi = Math.min(mi, a);
      ma = Math.max(ma, a);
      total += a;
    }
    total = 1L * ma * n - total;

    if (c1 * 2 <= c2 || n <= 2) {
      return (int) ((total * c1) % mod);
    }

    long op1 = Math.max(0L, (ma - mi) * 2L - total);
    long op2 = total - op1;
    long res = (op1 + op2 % 2) * c1 + op2 / 2 * c2;

    total += op1 / (n - 2) * n;
    op1 %= n - 2;
    op2 = total - op1;
    res = Math.min(res, (op1 + op2 % 2) * c1 + op2 / 2 * c2);

    for (int i = 0; i < 2; i++) {
      total += n;
      res = Math.min(res, total % 2 * c1 + total / 2 * c2);
    }

    return (int) (res % mod);
  }
}

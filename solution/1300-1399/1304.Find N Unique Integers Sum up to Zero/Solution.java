class Solution {
  public int[] sumZero(int n) {
    if (n == 2)
      return new int[] { 1, -1 };
    int preSum = 0;
    int[] ret = new int[n];
    for (int i = 0; i < n - 1; i++) {
      preSum += i;
      ret[i] = i;
    }
    ret[n - 1] = -preSum;
    return ret;
  }
}
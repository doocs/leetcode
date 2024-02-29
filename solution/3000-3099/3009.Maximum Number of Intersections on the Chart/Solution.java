class Solution {
  public int maxIntersectionCount(int[] y) {
    final int n = y.length;
    int ans = 0;
    int intersectionCount = 0;
    TreeMap<Integer, Integer> line = new TreeMap<>();

    for (int i = 1; i < n; ++i) {
      final int start = 2 * y[i - 1];
      final int end = 2 * y[i] + (i == n - 1 ? 0 : y[i] > y[i - 1] ? -1 : 1);
      line.merge(Math.min(start, end), 1, Integer::sum);
      line.merge(Math.max(start, end) + 1, -1, Integer::sum);
    }

    for (final int count : line.values()) {
      intersectionCount += count;
      ans = Math.max(ans, intersectionCount);
    }

    return ans;
  }
}
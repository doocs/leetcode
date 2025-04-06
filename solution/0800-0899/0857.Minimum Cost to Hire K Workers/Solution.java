class Solution {
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    double ans = Double.MAX_VALUE;
    int qualitySum = 0;
    // (wagePerQuality, quality) sorted by wagePerQuality
    Pair<Double, Integer>[] workers = new Pair[quality.length];
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < quality.length; ++i)
      workers[i] = new Pair<>((double) wage[i] / quality[i], quality[i]);

    Arrays.sort(workers, (a, b) -> Double.compare(a.getKey(), b.getKey()));

    for (Pair<Double, Integer> worker : workers) {
      final double wagePerQuality = worker.getKey();
      final int q = worker.getValue();
      maxHeap.offer(q);
      qualitySum += q;
      if (maxHeap.size() > k)
        qualitySum -= maxHeap.poll();
      if (maxHeap.size() == k)
        ans = Math.min(ans, qualitySum * wagePerQuality);
    }

    return ans;
  }
}

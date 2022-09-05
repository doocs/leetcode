class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<List<Integer>> pq = new PriorityQueue<>(
            (p1, p2) -> { return p2.get(0) + p2.get(1) - (p1.get(0) + p1.get(1)); });
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                pq.offer(List.of(nums1[i], nums2[j]));
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return new ArrayList<>(pq);
    }
}

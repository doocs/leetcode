class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        int[] d = new int[n + 1];
        int s = 0, j = 0;
        for (int i = 0; i < n; i++) {
            s += d[i];
            while (j < queries.length && queries[j][0] <= i) {
                pq.offer(queries[j][1]);
                j++;
            }
            while (s < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                s++;
                d[pq.poll() + 1]--;
            }
            if (s < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }
}

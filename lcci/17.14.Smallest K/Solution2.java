class Solution {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int v : arr) {
            q.offer(v);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!q.isEmpty()) {
            ans[i++] = q.poll();
        }
        return ans;
    }
}
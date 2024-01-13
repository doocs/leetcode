class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int x : arr) {
            q.offer(x);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = q.poll();
        }
        return ans;
    }
}
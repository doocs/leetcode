class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> bigRoot = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int e : arr) {
            if (bigRoot.size() < k) {
                bigRoot.offer(e);
            } else if (e < bigRoot.peek()) {
                bigRoot.poll();
                bigRoot.offer(e);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = bigRoot.poll();
        }
        return res;
    }
}
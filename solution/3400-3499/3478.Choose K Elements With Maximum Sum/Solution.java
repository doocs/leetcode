class Solution {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums1[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long s = 0;
        long[] ans = new long[n];
        int j = 0;
        for (int h = 0; h < n; ++h) {
            int x = arr[h][0], i = arr[h][1];
            while (j < h && arr[j][0] < x) {
                int y = nums2[arr[j][1]];
                pq.offer(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.poll();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
}

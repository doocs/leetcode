class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < items.length; ++i) {
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = 0, right = items.length;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (items[mid][0] > queries[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left > 0) {
                ans[i] = items[left - 1][1];
            }
        }
        return ans;
    }
}
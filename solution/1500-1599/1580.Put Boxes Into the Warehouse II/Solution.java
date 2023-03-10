class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int n = warehouse.length;
        int[] left = new int[n];
        int[] right = new int[n];
        final int inf = 1 << 30;
        left[0] = inf;
        right[n - 1] = inf;
        for (int i = 1; i < n; ++i) {
            left[i] = Math.min(left[i - 1], warehouse[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            right[i] = Math.min(right[i + 1], warehouse[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            warehouse[i] = Math.min(warehouse[i], Math.max(left[i], right[i]));
        }
        Arrays.sort(boxes);
        Arrays.sort(warehouse);
        int ans = 0, i = 0;
        for (int x : boxes) {
            while (i < n && warehouse[i] < x) {
                ++i;
            }
            if (i == n) {
                break;
            }
            ++ans;
            ++i;
        }
        return ans;
    }
}
class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] q) {
        int n = nums1.length, m = q.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }
        int[][] b = new int[m][3];
        for (int i = 0; i < m; i++) {
            b[i][0] = q[i][0];
            b[i][1] = q[i][1];
            b[i][2] = i;
        }
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(b, (o1, o2) -> o1[0] - o2[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[m];
        int max = -1;
        for (int i = m - 1, j = n - 1; i >= 0; i--) {
            int x = b[i][0], y = b[i][1], idx = b[i][2];
            while (j >= 0 && a[j][0] >= x) {
                if (max < a[j][1]) {
                    max = a[j][1];
                    Integer key = map.floorKey(a[j][1]);
                    while (key != null && map.get(key) <= a[j][0] + a[j][1]) {
                        map.remove(key);
                        key = map.floorKey(key);
                    }
                    map.put(max, a[j][0] + a[j][1]);
                }
                j--;
            }
            Integer key = map.ceilingKey(y);
            if (key == null)
                res[idx] = -1;
            else
                res[idx] = map.get(key);
        }
        return res;
    }
}
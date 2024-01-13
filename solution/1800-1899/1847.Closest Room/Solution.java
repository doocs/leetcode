class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int k = queries.length;
        Arrays.sort(rooms, (a, b) -> a[1] - b[1]);
        Integer[] idx = new Integer[k];
        for (int i = 0; i < k; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i][1] - queries[j][1]);
        int i = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] room : rooms) {
            tm.merge(room[0], 1, Integer::sum);
        }
        int[] ans = new int[k];
        Arrays.fill(ans, -1);
        for (int j : idx) {
            int prefer = queries[j][0], minSize = queries[j][1];
            while (i < n && rooms[i][1] < minSize) {
                if (tm.merge(rooms[i][0], -1, Integer::sum) == 0) {
                    tm.remove(rooms[i][0]);
                }
                ++i;
            }
            if (i == n) {
                break;
            }
            Integer p = tm.ceilingKey(prefer);
            if (p != null) {
                ans[j] = p;
            }
            p = tm.floorKey(prefer);
            if (p != null && (ans[j] == -1 || ans[j] - prefer >= prefer - p)) {
                ans[j] = p;
            }
        }
        return ans;
    }
}
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int m = slots1.length, n = slots2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);
            if (end - start >= duration) {
                return Arrays.asList(start, start + duration);
            }
            if (slots1[i][1] < slots2[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return Collections.emptyList();
    }
}
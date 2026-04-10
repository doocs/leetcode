class Solution {
    public int maximumTeamSize(int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = startTime[i];
            intervals[i][1] = endTime[i];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int ans = 0;
        for (int[] it : intervals) {
            int l = it[0], r = it[1];

            int i = search(endTime, l - 1);
            int j = search(startTime, r);

            ans = Math.max(ans, j - i);
        }

        return ans;
    }

    private int search(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

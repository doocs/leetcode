class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int mi = time[0];
        for (int v : time) {
            mi = Math.min(mi, v);
        }
        long left = 1, right = (long) mi * totalTrips;
        while (left < right) {
            long cnt = 0;
            long mid = (left + right) >> 1;
            for (int v : time) {
                cnt += mid / v;
            }
            if (cnt >= totalTrips) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
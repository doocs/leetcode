class Solution {
    public long repairCars(int[] ranks, int cars) {
        long left = 0, right = 1L * ranks[0] * cars * cars;
        while (left < right) {
            long mid = (left + right) >> 1;
            long cnt = 0;
            for (int r : ranks) {
                cnt += Math.sqrt(mid / r);
            }
            if (cnt >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
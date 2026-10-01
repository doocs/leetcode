class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int left = 0, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(houses, heaters, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] houses, int[] heaters, int r) {
        int m = houses.length, n = heaters.length;
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) {
                return false;
            }
            int mi = heaters[j] - r;
            int mx = heaters[j] + r;
            if (houses[i] < mi) {
                return false;
            }
            if (houses[i] > mx) {
                ++j;
            } else {
                ++i;
            }
        }
        return true;
    }
}

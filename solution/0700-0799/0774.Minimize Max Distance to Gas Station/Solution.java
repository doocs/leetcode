class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0, right = 1e8;
        while (right - left > 1e-6) {
            double mid = (left + right) / 2.0;
            if (check(mid, stations, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private boolean check(double x, int[] stations, int k) {
        int s = 0;
        for (int i = 0; i < stations.length - 1; ++i) {
            s += (int) ((stations[i + 1] - stations[i]) / x);
        }
        return s <= k;
    }
}
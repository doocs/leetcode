class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length - 1 >= hour) {
            return -1;
        }
        int l = 1, r = 10000000;
        while (l < r) {
            int m = (l + r) >> 1;
            if (arriveOnTime(dist, m, hour)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean arriveOnTime(int[] dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.length; ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.length - 1 ? cost : Math.ceil(cost));
        }
        return res <= hour;
    }
}
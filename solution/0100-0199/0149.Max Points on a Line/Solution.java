class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return n;
        }
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            Map<String, Integer> kCounter = new HashMap<>();
            int max = 0;
            int duplicate = 0;
            for (int j = i + 1; j < n; ++j) {
                int deltaX = points[i][0] - points[j][0];
                int deltaY = points[i][1] - points[j][1];
                if (deltaX == 0 && deltaY == 0) {
                    ++duplicate;
                    continue;
                }
                int gcd = gcd(deltaX, deltaY);
                int dX = deltaX / gcd;
                int dY = deltaY / gcd;
                String key = dX + "." + dY;
                kCounter.put(key, kCounter.getOrDefault(key, 0) + 1);
                max = Math.max(max, kCounter.get(key));
            }
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
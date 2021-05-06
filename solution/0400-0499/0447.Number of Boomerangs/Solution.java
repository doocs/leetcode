class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> distanceCounter = new HashMap<>();
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                distanceCounter.put(distance, distanceCounter.getOrDefault(distance, 0) + 1);
            }
            for (int val : distanceCounter.values()) {
                number += val * (val - 1);
            }
        }
        return number;
    }
}
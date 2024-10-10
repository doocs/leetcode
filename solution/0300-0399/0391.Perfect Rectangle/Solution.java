import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int area = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1];
        int maxX = rectangles[0][2], maxY = rectangles[0][3];
        Map<String, Integer> cnt = new HashMap<>();

        for (int[] r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);

            minX = Math.min(minX, r[0]);
            minY = Math.min(minY, r[1]);
            maxX = Math.max(maxX, r[2]);
            maxY = Math.max(maxY, r[3]);

            String[] points = {
                r[0] + "," + r[1],
                r[0] + "," + r[3],
                r[2] + "," + r[3],
                r[2] + "," + r[1]
            };

            for (String point : points) {
                cnt.put(point, cnt.getOrDefault(point, 0) + 1);
            }
        }

        if (area != (maxX - minX) * (maxY - minY)
            || cnt.getOrDefault(minX + "," + minY, 0) != 1
            || cnt.getOrDefault(minX + "," + maxY, 0) != 1
            || cnt.getOrDefault(maxX + "," + maxY, 0) != 1
            || cnt.getOrDefault(maxX + "," + minY, 0) != 1) {
            return false;
        }

        cnt.remove(minX + "," + minY);
        cnt.remove(minX + "," + maxY);
        cnt.remove(maxX + "," + maxY);
        cnt.remove(maxX + "," + minY);

        for (int c : cnt.values()) {
            if (c != 2 && c != 4) return false;
        }

        return true;
    }
}

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1];
        int maxX = rectangles[0][2], maxY = rectangles[0][3];
        Map<Pair, Integer> cnt = new HashMap<>();

        for (int[] r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);

            minX = Math.min(minX, r[0]);
            minY = Math.min(minY, r[1]);
            maxX = Math.max(maxX, r[2]);
            maxY = Math.max(maxY, r[3]);

            cnt.merge(new Pair(r[0], r[1]), 1, Integer::sum);
            cnt.merge(new Pair(r[0], r[3]), 1, Integer::sum);
            cnt.merge(new Pair(r[2], r[3]), 1, Integer::sum);
            cnt.merge(new Pair(r[2], r[1]), 1, Integer::sum);
        }

        if (area != (long) (maxX - minX) * (maxY - minY)
            || cnt.getOrDefault(new Pair(minX, minY), 0) != 1
            || cnt.getOrDefault(new Pair(minX, maxY), 0) != 1
            || cnt.getOrDefault(new Pair(maxX, maxY), 0) != 1
            || cnt.getOrDefault(new Pair(maxX, minY), 0) != 1) {
            return false;
        }

        cnt.remove(new Pair(minX, minY));
        cnt.remove(new Pair(minX, maxY));
        cnt.remove(new Pair(maxX, maxY));
        cnt.remove(new Pair(maxX, minY));

        return cnt.values().stream().allMatch(c -> c == 2 || c == 4);
    }

    private static class Pair {
        final int first;
        final int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}

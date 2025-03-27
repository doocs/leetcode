class Solution {
    // Helper class to mimic C++ pair<int, int>
    static class Pair {
        int value;
        int type;

        Pair(int value, int type) {
            this.value = value;
            this.type = type;
        }
    }

    private boolean countLineIntersections(List<Pair> coordinates) {
        int lines = 0;
        int overlap = 0;

        for (Pair coord : coordinates) {
            if (coord.type == 0) {
                overlap--;
            } else {
                overlap++;
            }

            if (overlap == 0) {
                lines++;
            }
        }

        return lines >= 3;
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        List<Pair> yCoordinates = new ArrayList<>();
        List<Pair> xCoordinates = new ArrayList<>();

        for (int[] rectangle : rectangles) {
            // rectangle = [x1, y1, x2, y2]
            yCoordinates.add(new Pair(rectangle[1], 1)); // y1, start
            yCoordinates.add(new Pair(rectangle[3], 0)); // y2, end

            xCoordinates.add(new Pair(rectangle[0], 1)); // x1, start
            xCoordinates.add(new Pair(rectangle[2], 0)); // x2, end
        }

        Comparator<Pair> comparator = (a, b) -> {
            if (a.value != b.value) return Integer.compare(a.value, b.value);
            return Integer.compare(a.type, b.type); // End (0) before Start (1)
        };

        Collections.sort(yCoordinates, comparator);
        Collections.sort(xCoordinates, comparator);

        return countLineIntersections(yCoordinates) || countLineIntersections(xCoordinates);
    }
}
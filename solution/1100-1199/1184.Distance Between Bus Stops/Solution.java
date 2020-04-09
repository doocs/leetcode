class Solution {
    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int length = 0;
        for (int i : distance) {
            length += i;
        }
        int min = Math.min(start, destination);
        int max = Math.max(start, destination);
        int length2 = 0;
        for (int i = min; i < max; i++) {
            length2 += distance[i];
        }
        return Math.min(length - length2, length2);
    }
}

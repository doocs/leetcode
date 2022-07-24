class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int a = 0, b = 0;
        for (int i = 0; i < distance.length; ++i) {
            if (i >= start && i < destination) {
                a += distance[i];
            } else {
                b += distance[i];
            }
        }
        return Math.min(a, b);
    }
}
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int s = Arrays.stream(distance).sum();
        int n = distance.length, t = 0;
        while (start != destination) {
            t += distance[start];
            start = (start + 1) % n;
        }
        return Math.min(t, s - t);
    }
}

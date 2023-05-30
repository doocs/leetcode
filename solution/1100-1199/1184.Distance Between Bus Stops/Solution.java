class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int s = Arrays.stream(distance).sum();
        int n = distance.length;
        int a = 0;
        while (start != destination) {
            a += distance[start];
            start = (start + 1) % n;
        }
        return Math.min(a, s - a);
    }
}
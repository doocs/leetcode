class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        s = sum(distance)
        t, n = 0, len(distance)
        while start != destination:
            t += distance[start]
            start = (start + 1) % n
        return min(t, s - t)

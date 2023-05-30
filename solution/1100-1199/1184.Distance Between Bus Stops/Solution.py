class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        a, n = 0, len(distance)
        while start != destination:
            a += distance[start]
            start = (start + 1) % n
        return min(a, sum(distance) - a)

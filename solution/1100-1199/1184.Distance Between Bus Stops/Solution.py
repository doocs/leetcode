class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        if start > destination:
            start, destination = destination, start
        a = sum(distance[start:destination])
        b = sum(distance[:start]) + sum(distance[destination:])
        return min(a, b)

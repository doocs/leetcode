class Solution:
    def minRefuelStops(
        self, target: int, startFuel: int, stations: List[List[int]]
    ) -> int:
        q = []
        prev = ans = 0
        stations.append([target, 0])
        for a, b in stations:
            d = a - prev
            startFuel -= d
            while startFuel < 0 and q:
                startFuel -= heappop(q)
                ans += 1
            if startFuel < 0:
                return -1
            heappush(q, -b)
            prev = a
        return ans

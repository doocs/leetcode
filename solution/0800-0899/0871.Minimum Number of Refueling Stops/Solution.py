class Solution:
    def minRefuelStops(
        self, target: int, startFuel: int, stations: List[List[int]]
    ) -> int:
        pq = []
        ans = pre = 0
        stations.append([target, 0])
        for pos, fuel in stations:
            dist = pos - pre
            startFuel -= dist
            while startFuel < 0 and pq:
                startFuel -= heappop(pq)
                ans += 1
            if startFuel < 0:
                return -1
            heappush(pq, -fuel)
            pre = pos
        return ans

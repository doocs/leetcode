class Solution:
    def maxPerformance(
        self, n: int, speed: List[int], efficiency: List[int], k: int
    ) -> int:
        team = [(s, e) for s, e in zip(speed, efficiency)]
        team.sort(key=lambda x: -x[1])
        q = []
        t = 0
        ans = 0
        mod = int(1e9) + 7
        for s, e in team:
            t += s
            ans = max(ans, t * e)
            heappush(q, s)
            if len(q) >= k:
                t -= heappop(q)
        return ans % mod

class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        g = defaultdict(list)
        l, r = inf, 0
        for s, e in events:
            g[s].append(e)
            l = min(l, s)
            r = max(r, e)
        pq = []
        ans = 0
        for s in range(l, r + 1):
            while pq and pq[0] < s:
                heappop(pq)
            for e in g[s]:
                heappush(pq, e)
            if pq:
                heappop(pq)
                ans += 1
        return ans

class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        d = defaultdict(list)
        i, j = inf, 0
        for s, e in events:
            d[s].append(e)
            i = min(i, s)
            j = max(j, e)
        h = []
        ans = 0
        for s in range(i, j + 1):
            while h and h[0] < s:
                heappop(h)
            for e in d[s]:
                heappush(h, e)
            if h:
                ans += 1
                heappop(h)
        return ans

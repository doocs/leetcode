class Solution:
    def maxRequests(self, requests: list[list[int]], k: int, window: int) -> int:
        g = defaultdict(list)
        for u, t in requests:
            g[u].append(t)
        ans = len(requests)
        for ts in g.values():
            ts.sort()
            kept = deque()
            for t in ts:
                while kept and t - kept[0] > window:
                    kept.popleft()
                if len(kept) < k:
                    kept.append(t)
                else:
                    ans -= 1
        return ans

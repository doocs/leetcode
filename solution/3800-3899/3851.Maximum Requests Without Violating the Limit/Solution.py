class Solution:
    def maxRequests(self, requests: list[list[int]], k: int, window: int) -> int:
        g = defaultdict(list)
        for u, t in requests:
            g[u].append(t)
        ans = 0
        for ts in g.values():
            ts.sort()
            kept = deque()
            deletions = 0
            for t in ts:
                while kept and t - kept[0] > window:
                    kept.popleft()
                kept.append(t)
                if len(kept) > k:
                    kept.pop()
                    deletions += 1
            ans += len(ts) - deletions
        return ans

class Solution:
    def resultsArray(self, queries: list[list[int]], k: int) -> list[int]:
        ans = []
        pq = []
        for i, (x, y) in enumerate(queries):
            heappush(pq, -(abs(x) + abs(y)))
            if i >= k:
                heappop(pq)
            ans.append(-pq[0] if i >= k - 1 else -1)
        return ans

class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        h = []
        for p in piles:
            heappush(h, -p)
        for _ in range(k):
            p = -heappop(h)
            heappush(h, -((p + 1) >> 1))
        return -sum(h)

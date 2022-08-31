class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        h = [-v for v in stones]
        heapify(h)
        while len(h) > 1:
            x = heappop(h)
            y = heappop(h)
            if x != y:
                heappush(h, x - y)
        return 0 if len(h) == 0 else -h[0]

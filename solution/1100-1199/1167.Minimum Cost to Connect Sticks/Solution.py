class Solution:
    def connectSticks(self, sticks: List[int]) -> int:
        h = []
        for s in sticks:
            heapq.heappush(h, s)
        res = 0
        while len(h) > 1:
            val = heapq.heappop(h) + heapq.heappop(h)
            res += val
            heapq.heappush(h, val)
        return res

class Solution:
    def connectSticks(self, sticks: List[int]) -> int:
        h = []
        for s in sticks:
            heappush(h, s)
        res = 0
        while len(h) > 1:
            val = heappop(h) + heappop(h)
            res += val
            heappush(h, val)
        return res

class Solution:
    def smallestK(self, arr: list[int], k: int) -> list[int]:
        h = []
        for v in arr:
            heappush(h, -v)
            if len(h) > k:
                heappop(h)
        return [-v for v in h]

class Solution:
    def getLeastNumbers(self, arr: list[int], k: int) -> list[int]:
        h = []
        for x in arr:
            heappush(h, -x)
            if len(h) > k:
                heappop(h)
        return [-x for x in h]

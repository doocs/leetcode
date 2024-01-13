class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        h = []
        for x in arr:
            heappush(h, -x)
            if len(h) > k:
                heappop(h)
        return [-x for x in h]

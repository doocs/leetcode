class Solution:
    def storeWater(self, bucket: List[int], vat: List[int]) -> int:
        mx = max(vat)
        if mx == 0:
            return 0
        ans = inf
        for x in range(1, mx + 1):
            y = sum(max(0, (v + x - 1) // x - b) for v, b in zip(vat, bucket))
            ans = min(ans, x + y)
        return ans

class Solution:
    def magicTower(self, nums: List[int]) -> int:
        q = []
        blood = 1
        ans = v = 0
        for x in nums:
            if x < 0:
                heappush(q, x)
            blood += x
            if blood <= 0:
                ans += 1
                v += q[0]
                blood -= heappop(q)
        blood += v
        return -1 if blood <= 0 else ans

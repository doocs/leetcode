class Solution:
    def findKOr(self, nums: list[int], k: int) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(x >> i & 1 for x in nums)
            if cnt >= k:
                ans |= 1 << i
        return ans

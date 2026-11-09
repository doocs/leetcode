class Solution:
    def minOperations(self, nums: list[int]) -> int:
        ans = v = 0
        for x in nums:
            x ^= v
            if x == 0:
                ans += 1
                v ^= 1
        return ans

class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            g = 0
            for x in nums[i:]:
                g = gcd(g, x)
                ans += g == k
        return ans

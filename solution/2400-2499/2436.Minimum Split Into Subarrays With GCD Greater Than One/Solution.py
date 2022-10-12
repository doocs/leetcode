class Solution:
    def minimumSplits(self, nums: List[int]) -> int:
        ans, x = 1, nums[0]
        for v in nums:
            x = gcd(x, v)
            if x == 1:
                x = v
                ans += 1
        return ans

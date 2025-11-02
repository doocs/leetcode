class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        nums.sort()
        a, b = nums[0], nums[1]
        c, d = nums[-2], nums[-1]
        x = 10**5
        return max(a * b * x, c * d * x, a * d * -x)

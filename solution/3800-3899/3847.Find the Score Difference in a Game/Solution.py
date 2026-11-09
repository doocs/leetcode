class Solution:
    def scoreDifference(self, nums: list[int]) -> int:
        ans, k = 0, 1
        for i, x in enumerate(nums):
            if x % 2:
                k *= -1
            if i % 6 == 5:
                k *= -1
            ans += k * x
        return ans

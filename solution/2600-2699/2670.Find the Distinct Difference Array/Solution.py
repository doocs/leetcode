class Solution:
    def distinctDifferenceArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        for i in range(n):
            a = len(set(nums[: i + 1]))
            b = len(set(nums[i + 1 :]))
            ans[i] = a - b
        return ans

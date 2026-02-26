class Solution:
    def rob(self, nums: List[int], colors: List[int]) -> int:
        n = len(nums)
        f, g = 0, nums[0]
        for i in range(1, n):
            if colors[i - 1] == colors[i]:
                f, g = max(f, g), f + nums[i]
            else:
                f, g = max(f, g), max(f, g) + nums[i]
        return max(f, g)

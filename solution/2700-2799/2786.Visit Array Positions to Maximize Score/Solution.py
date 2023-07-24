class Solution:
    def maxScore(self, nums: List[int], x: int) -> int:
        f = [-inf] * 2
        f[nums[0] & 1] = nums[0]
        for v in nums[1:]:
            f[v & 1] = max(f[v & 1] + v, f[v & 1 ^ 1] + v - x)
        return max(f)

class Solution:
    def partitionArray(self, nums: List[int], k: int) -> int:
        nums.sort()
        d, ans = 0, 1
        for a, b in pairwise(nums):
            if (t := b - a) + d <= k:
                d += t
            else:
                d, ans = 0, ans + 1
        return ans

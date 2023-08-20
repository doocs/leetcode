class Solution:
    def countPairs(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans = 0
        for j, x in enumerate(nums):
            i = bisect_left(nums, target - x, hi=j)
            ans += i
        return ans

class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))

        ans = n
        for i, start in enumerate(nums):
            end = start + n - 1
            j = bisect_right(nums, end)
            remainLen = j - i
            ans = min(ans, n - remainLen)
        return ans

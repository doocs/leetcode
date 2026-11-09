class Solution:
    def smallestAbsent(self, nums: list[int]) -> int:
        s = set(nums)
        ans = max(1, sum(nums) // len(nums) + 1)
        while ans in s:
            ans += 1
        return ans

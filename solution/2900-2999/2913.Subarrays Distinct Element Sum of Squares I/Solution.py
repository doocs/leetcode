class Solution:
    def sumCounts(self, nums: list[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(n):
            s = set()
            for j in range(i, n):
                s.add(nums[j])
                ans += len(s) * len(s)
        return ans

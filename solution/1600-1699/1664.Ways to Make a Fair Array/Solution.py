class Solution:
    def waysToMakeFair(self, nums: list[int]) -> int:
        s = sum(nums)
        r0 = sum(nums[::2])
        r = [r0, s - r0]
        l = [0, 0]
        ans = 0
        for i, x in enumerate(nums):
            r[i % 2] -= x
            if l[0] + r[1] == l[1] + r[0]:
                ans += 1
            l[i % 2] += x
        return ans

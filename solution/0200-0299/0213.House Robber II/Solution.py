class Solution:
    def rob(self, nums: List[int]) -> int:
        def robRange(nums, l, r):
            a, b = 0, nums[l]
            for num in nums[l + 1 : r + 1]:
                a, b = b, max(num + a, b)
            return b

        n = len(nums)
        if n == 1:
            return nums[0]
        s1, s2 = robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
        return max(s1, s2)

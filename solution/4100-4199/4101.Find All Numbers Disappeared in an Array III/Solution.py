class Solution:
    def findDisappearedNumbers(
        self, nums: list[int], lower: int, upper: int
    ) -> list[list[int]]:
        nums.sort()
        l = bisect_left(nums, lower)
        r = bisect_left(nums, upper + 1)
        ans = []
        pre = lower
        for i in range(l, r):
            cur = nums[i]
            if pre < cur:
                ans.append([pre, cur - 1])
            pre = cur + 1
        if pre <= upper:
            ans.append([pre, upper])
        return ans

class Solution:
    def countSmallerOppositeParity(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * n
        sl = [SortedList(), SortedList()]
        for i in range(n - 1, -1, -1):
            ans[i] = sl[nums[i] & 1 ^ 1].bisect_left(nums[i])
            sl[nums[i] & 1].add(nums[i])
        return ans

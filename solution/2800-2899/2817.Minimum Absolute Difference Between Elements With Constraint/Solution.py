from sortedcontainers import SortedList


class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        sl = SortedList()
        ans = inf
        for i in range(x, len(nums)):
            sl.add(nums[i - x])
            p = bisect_left(sl, nums[i])
            if p < len(sl):
                ans = min(ans, abs(nums[i] - sl[p]))
            if p:
                ans = min(ans, abs(nums[i] - sl[p - 1]))
        return ans

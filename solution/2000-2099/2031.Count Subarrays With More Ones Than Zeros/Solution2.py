from sortedcontainers import SortedList


class Solution:
    def subarraysWithMoreZerosThanOnes(self, nums: List[int]) -> int:
        sl = SortedList([0])
        mod = 10**9 + 7
        ans = s = 0
        for x in nums:
            s += x or -1
            ans += sl.bisect_left(s)
            ans %= mod
            sl.add(s)
        return ans

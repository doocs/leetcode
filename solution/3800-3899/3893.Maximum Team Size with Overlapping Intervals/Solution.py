class Solution:
    def maximumTeamSize(self, startTime: list[int], endTime: list[int]) -> int:
        intervals = list(zip(startTime, endTime))
        startTime.sort()
        endTime.sort()
        ans = 0
        for l, r in intervals:
            i = bisect_right(endTime, l - 1)
            j = bisect_right(startTime, r)
            ans = max(ans, j - i)
        return ans

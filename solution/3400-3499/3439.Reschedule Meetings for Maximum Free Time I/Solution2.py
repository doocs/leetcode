class Solution:
    def maxFreeTime(
        self, eventTime: int, k: int, startTime: List[int], endTime: List[int]
    ) -> int:
        def f(i: int) -> int:
            if i == 0:
                return startTime[0]
            if i == len(endTime):
                return eventTime - endTime[-1]
            return startTime[i] - endTime[i - 1]

        ans = s = 0
        for i in range(len(endTime) + 1):
            s += f(i)
            if i >= k:
                ans = max(ans, s)
                s -= f(i - k)
        return ans

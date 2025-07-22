class Solution:
    def maxFreeTime(
        self, eventTime: int, startTime: List[int], endTime: List[int]
    ) -> int:
        n = len(startTime)
        pre = [0] * n
        suf = [0] * n
        pre[0] = startTime[0]
        suf[n - 1] = eventTime - endTime[-1]
        for i in range(1, n):
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1])
        for i in range(n - 2, -1, -1):
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i])
        ans = 0
        for i in range(n):
            l = 0 if i == 0 else endTime[i - 1]
            r = eventTime if i == n - 1 else startTime[i + 1]
            w = endTime[i] - startTime[i]
            ans = max(ans, r - l - w)
            if i and pre[i - 1] >= w:
                ans = max(ans, r - l)
            elif i + 1 < n and suf[i + 1] >= w:
                ans = max(ans, r - l)
        return ans

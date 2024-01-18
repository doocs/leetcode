class Solution:
    def jobScheduling(
        self, startTime: List[int], endTime: List[int], profit: List[int]
    ) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            j = bisect_left(idx, endTime[idx[i]], key=lambda i: startTime[i])
            return max(dfs(i + 1), profit[idx[i]] + dfs(j))

        n = len(startTime)
        idx = sorted(range(n), key=lambda i: startTime[i])
        return dfs(0)

class Solution:
    def maxFreeTime(
        self, eventTime: int, startTime: List[int], endTime: List[int]
    ) -> int:
        n = len(startTime)
        res = 0

        left_gaps = [0] * n
        left_gaps[0] = startTime[0]
        for meet in range(1, n):
            left_gaps[meet] = max(
                left_gaps[meet - 1], startTime[meet] - endTime[meet - 1]
            )

        right_gaps = [0] * n
        right_gaps[n - 1] = eventTime - endTime[-1]
        for meet in range(n - 2, -1, -1):
            right_gaps[meet] = max(
                right_gaps[meet + 1], startTime[meet + 1] - endTime[meet]
            )

        for meet in range(n):
            left_gap = (
                left_gaps[meet] if meet == 0 else startTime[meet] - endTime[meet - 1]
            )
            right_gap = (
                right_gaps[meet]
                if meet == n - 1
                else startTime[meet + 1] - endTime[meet]
            )

            interval = 0

            if (
                meet != 0
                and left_gaps[meet - 1] >= (endTime[meet] - startTime[meet])
                or meet != n - 1
                and right_gaps[meet + 1] >= (endTime[meet] - startTime[meet])
            ):
                interval = endTime[meet] - startTime[meet]

            res = max(res, left_gap + interval + right_gap)

        return res
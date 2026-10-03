class Solution:
    def jobScheduling(
        self, startTime: List[int], endTime: List[int], profit: List[int]
    ) -> int:
        jobs = sorted(zip(startTime, endTime, profit))
        n = len(profit)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            _, e, p = jobs[i]
            j = bisect_left(jobs, e, lo=i + 1, key=lambda x: x[0])
            f[i] = max(f[i + 1], p + f[j])
        return f[0]

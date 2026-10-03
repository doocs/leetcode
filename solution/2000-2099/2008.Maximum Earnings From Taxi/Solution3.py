class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        rides.sort()
        m = len(rides)
        f = [0] * (m + 1)
        for i in range(m - 1, -1, -1):
            st, ed, tip = rides[i]
            j = bisect_left(rides, ed, lo=i + 1, key=lambda x: x[0])
            f[i] = max(f[i + 1], f[j] + ed - st + tip)
        return f[0]

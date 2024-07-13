class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        m = days[-1]
        f = [0] * (m + 1)
        valid = [1, 7, 30]
        j = 0
        for i in range(1, m + 1):
            if i == days[j]:
                f[i] = inf
                for c, v in zip(costs, valid):
                    f[i] = min(f[i], f[max(0, i - v)] + c)
                j += 1
            else:
                f[i] = f[i - 1]
        return f[m]

class Solution:
    def firstDayBeenInAllRooms(self, nextVisit: List[int]) -> int:
        n = len(nextVisit)
        f = [0] * n
        mod = 10**9 + 7
        for i in range(1, n):
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1) % mod
        return f[-1]

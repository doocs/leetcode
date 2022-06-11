class Solution:
    def knightDialer(self, n: int) -> int:
        if n == 1:
            return 10
        f = [1] * 10
        for _ in range(n - 1):
            t = [0] * 10
            t[0] = f[4] + f[6]
            t[1] = f[6] + f[8]
            t[2] = f[7] + f[9]
            t[3] = f[4] + f[8]
            t[4] = f[0] + f[3] + f[9]
            t[6] = f[0] + f[1] + f[7]
            t[7] = f[2] + f[6]
            t[8] = f[1] + f[3]
            t[9] = f[2] + f[4]
            f = t
        return sum(t) % (10**9 + 7)

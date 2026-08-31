class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        mod = 10**9 + 7
        m = r - l + 1
        up = [1] * m
        down = [1] * m
        for _ in range(n - 1):
            pre = [0] * (m + 1)
            suf = [0] * (m + 1)
            for i in range(m):
                pre[i + 1] = (pre[i] + down[i]) % mod
            for i in range(m - 1, -1, -1):
                suf[i] = (suf[i + 1] + up[i]) % mod
            up = pre[:m]
            down = suf[1:]
        return sum(up + down) % mod

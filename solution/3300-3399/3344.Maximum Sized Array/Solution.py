mx = 1330
f = [0] * mx
for i in range(1, mx):
    f[i] = f[i - 1] + i
    for j in range(i):
        f[i] += 2 * (i | j)


class Solution:
    def maxSizedArray(self, s: int) -> int:
        l, r = 1, mx
        while l < r:
            m = (l + r + 1) >> 1
            if f[m - 1] * (m - 1) * m // 2 <= s:
                l = m
            else:
                r = m - 1
        return l

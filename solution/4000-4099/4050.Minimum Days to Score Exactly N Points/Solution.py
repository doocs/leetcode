mx = 10**5 + 1
f = [inf] * mx
f[0] = -1
for i in range(1, mx):
    j = 1
    while (s := (1 + j) * j // 2) <= i:
        f[i] = min(f[i], f[i - s] + j + 1)
        j += 1


class Solution:
    def minDays(self, n: int) -> int:
        return f[n]

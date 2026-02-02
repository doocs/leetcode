mx = 50
c = [[0] * (mx + 1) for _ in range(mx)]
for i in range(mx):
    c[i][0] = 1
    for j in range(1, i + 1):
        c[i][j] = c[i - 1][j - 1] + c[i - 1][j]


class Solution:
    def nthSmallest(self, n: int, k: int) -> int:
        ans = 0
        for i in range(49, -1, -1):
            if n > c[i][k]:
                n -= c[i][k]
                ans |= 1 << i
                k -= 1
                if k == 0:
                    break
        return ans

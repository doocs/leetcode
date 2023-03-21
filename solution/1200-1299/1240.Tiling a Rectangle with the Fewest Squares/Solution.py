class Solution:
    def tilingRectangle(self, n: int, m: int) -> int:
        def dfs(i, j, t):
            nonlocal ans
            if j == m:
                i += 1
                j = 0
            if i == n:
                ans = t
                return
            if filled[i] >> j & 1:
                dfs(i, j + 1, t)
            elif t + 1 < ans:
                r = c = 0
                for k in range(i, n):
                    if filled[k] >> j & 1:
                        break
                    r += 1
                for k in range(j, m):
                    if filled[i] >> k & 1:
                        break
                    c += 1
                mx = r if r < c else c
                for w in range(1, mx + 1):
                    for k in range(w):
                        filled[i + w - 1] |= 1 << (j + k)
                        filled[i + k] |= 1 << (j + w - 1)
                    dfs(i, j + w, t + 1)
                for x in range(i, i + mx):
                    for y in range(j, j + mx):
                        filled[x] ^= 1 << y

        ans = n * m
        filled = [0] * n
        dfs(0, 0, 0)
        return ans

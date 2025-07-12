@cache
def dfs(l: int, r: int, n: int):
    if l + r == n - 1:
        return [1, 1]
    res = [inf, -inf]
    m = n >> 1
    for i in range(1 << m):
        win = [False] * n
        for j in range(m):
            if i >> j & 1:
                win[j] = True
            else:
                win[n - 1 - j] = True
        if n & 1:
            win[m] = True
        win[n - 1 - l] = win[n - 1 - r] = False
        win[l] = win[r] = True
        a = b = c = 0
        for j in range(n):
            if j == l:
                a = c
            if j == r:
                b = c
            if win[j]:
                c += 1
        x, y = dfs(a, b, c)
        res[0] = min(res[0], x + 1)
        res[1] = max(res[1], y + 1)
    return res


class Solution:
    def earliestAndLatest(
        self, n: int, firstPlayer: int, secondPlayer: int
    ) -> List[int]:
        return dfs(firstPlayer - 1, secondPlayer - 1, n)

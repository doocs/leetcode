from sortedcontainers import SortedDict


class Solution:
    def oddEvenJumps(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, k: int) -> bool:
            if i == n - 1:
                return True
            if g[i][k] == -1:
                return False
            return dfs(g[i][k], k ^ 1)

        n = len(arr)
        g = [[0] * 2 for _ in range(n)]
        sd = SortedDict()
        for i in range(n - 1, -1, -1):
            j = sd.bisect_left(arr[i])
            g[i][1] = sd.values()[j] if j < len(sd) else -1
            j = sd.bisect_right(arr[i]) - 1
            g[i][0] = sd.values()[j] if j >= 0 else -1
            sd[arr[i]] = i
        return sum(dfs(i, 1) for i in range(n))

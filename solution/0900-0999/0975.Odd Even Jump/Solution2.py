class Solution:
    def oddEvenJumps(self, arr: List[int]) -> int:
        n = len(arr)
        g = [[-1] * 2 for _ in range(n)]
        sd = SortedDict()
        for i in range(n - 1, -1, -1):
            j = sd.bisect_left(arr[i])
            g[i][1] = sd.values()[j] if j < len(sd) else -1
            j = sd.bisect_right(arr[i]) - 1
            g[i][0] = sd.values()[j] if j >= 0 else -1
            sd[arr[i]] = i
        f = [[False] * 2 for _ in range(n)]
        f[-1][0] = f[-1][1] = True
        for i in range(n - 2, -1, -1):
            for k in range(2):
                j = g[i][k]
                if j != -1:
                    f[i][k] = f[j][k ^ 1]
        return sum(f[i][1] for i in range(n))

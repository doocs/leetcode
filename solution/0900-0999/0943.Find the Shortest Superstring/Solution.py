class Solution:
    def shortestSuperstring(self, words: List[str]) -> str:
        n = len(words)
        g = [[0] * n for _ in range(n)]
        for i, a in enumerate(words):
            for j, b in enumerate(words):
                if i != j:
                    for k in range(min(len(a), len(b)), 0, -1):
                        if a[-k:] == b[:k]:
                            g[i][j] = k
                            break
        dp = [[0] * n for _ in range(1 << n)]
        p = [[-1] * n for _ in range(1 << n)]
        for i in range(1 << n):
            for j in range(n):
                if (i >> j) & 1:
                    pi = i ^ (1 << j)
                    for k in range(n):
                        if (pi >> k) & 1:
                            v = dp[pi][k] + g[k][j]
                            if v > dp[i][j]:
                                dp[i][j] = v
                                p[i][j] = k
        j = 0
        for i in range(n):
            if dp[-1][i] > dp[-1][j]:
                j = i
        arr = [j]
        i = (1 << n) - 1
        while p[i][j] != -1:
            i, j = i ^ (1 << j), p[i][j]
            arr.append(j)
        arr = arr[::-1]
        vis = set(arr)
        arr.extend([j for j in range(n) if j not in vis])
        ans = [words[arr[0]]] + [words[j][g[i][j] :] for i, j in pairwise(arr)]
        return ''.join(ans)

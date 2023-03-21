class Solution:
    def findRotateSteps(self, ring: str, key: str) -> int:
        m, n = len(key), len(ring)
        pos = defaultdict(list)
        for i, c in enumerate(ring):
            pos[c].append(i)
        f = [[inf] * n for _ in range(m)]
        for j in pos[key[0]]:
            f[0][j] = min(j, n - j) + 1
        for i in range(1, m):
            for j in pos[key[i]]:
                for k in pos[key[i - 1]]:
                    f[i][j] = min(
                        f[i][j], f[i - 1][k] + min(abs(j - k), n - abs(j - k)) + 1
                    )
        return min(f[-1][j] for j in pos[key[-1]])

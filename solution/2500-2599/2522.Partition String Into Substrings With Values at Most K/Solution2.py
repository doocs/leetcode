class Solution:
    def minimumPartition(self, s: str, k: int) -> int:
        n = len(s)
        f = [inf] * (n + 1)
        f[n] = 0
        for i in range(n - 1, -1, -1):
            v = 0
            for j in range(i, n):
                v = v * 10 + int(s[j])
                if v > k:
                    break
                f[i] = min(f[i], f[j + 1])
            f[i] += 1
        return f[0] if f[0] < inf else -1

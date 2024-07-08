class Solution:
    def minimumCost(self, target: str, words: List[str], costs: List[int]) -> int:
        base, mod = 13331, 998244353
        n = len(target)
        h = [0] * (n + 1)
        p = [1] * (n + 1)
        for i, c in enumerate(target, 1):
            h[i] = (h[i - 1] * base + ord(c)) % mod
            p[i] = (p[i - 1] * base) % mod
        f = [0] + [inf] * n
        ss = sorted(set(map(len, words)))
        d = defaultdict(lambda: inf)
        min = lambda a, b: a if a < b else b
        for w, c in zip(words, costs):
            x = 0
            for ch in w:
                x = (x * base + ord(ch)) % mod
            d[x] = min(d[x], c)
        for i in range(1, n + 1):
            for j in ss:
                if j > i:
                    break
                x = (h[i] - h[i - j] * p[j]) % mod
                f[i] = min(f[i], f[i - j] + d[x])
        return f[n] if f[n] < inf else -1

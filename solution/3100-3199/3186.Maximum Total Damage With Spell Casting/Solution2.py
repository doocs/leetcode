class Solution:
    def maximumTotalDamage(self, power: List[int]) -> int:
        n = len(power)
        cnt = Counter(power)
        power.sort()
        nxt = [bisect_right(power, x + 2, lo=i + 1) for i, x in enumerate(power)]
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            j = i + cnt[power[i]]
            a = f[j] if j <= n else 0
            b = power[i] * cnt[power[i]] + f[nxt[i]]
            f[i] = max(a, b)
        return f[0]

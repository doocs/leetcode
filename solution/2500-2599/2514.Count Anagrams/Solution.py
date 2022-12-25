mod = 10**9 + 7
f = [1]
for i in range(1, 10**5 + 1):
    f.append(f[-1] * i % mod)


class Solution:
    def countAnagrams(self, s: str) -> int:
        ans = 1
        for w in s.split():
            cnt = Counter(w)
            ans *= f[len(w)]
            ans %= mod
            for v in cnt.values():
                ans *= pow(f[v], -1, mod)
                ans %= mod
        return ans

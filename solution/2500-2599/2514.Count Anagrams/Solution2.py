class Solution:
    def countAnagrams(self, s: str) -> int:
        mod = 10**9 + 7
        ans = mul = 1
        for w in s.split():
            cnt = Counter()
            for i, c in enumerate(w, 1):
                cnt[c] += 1
                mul = mul * cnt[c] % mod
                ans = ans * i % mod
        return ans * pow(mul, -1, mod) % mod

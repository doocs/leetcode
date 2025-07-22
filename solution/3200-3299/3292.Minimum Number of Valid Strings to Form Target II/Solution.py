class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(self, s: List[str], base: int, mod: int):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def minValidStrings(self, words: List[str], target: str) -> int:
        def f(i: int) -> int:
            l, r = 0, min(n - i, m)
            while l < r:
                mid = (l + r + 1) >> 1
                sub = hashing.query(i + 1, i + mid)
                if sub in s[mid]:
                    l = mid
                else:
                    r = mid - 1
            return l

        base, mod = 13331, 998244353
        hashing = Hashing(target, base, mod)
        m = max(len(w) for w in words)
        s = [set() for _ in range(m + 1)]
        for w in words:
            h = 0
            for j, c in enumerate(w, 1):
                h = (h * base + ord(c)) % mod
                s[j].add(h)
        ans = last = mx = 0
        n = len(target)
        for i in range(n):
            dist = f(i)
            mx = max(mx, i + dist)
            if i == last:
                if i == mx:
                    return -1
                last = mx
                ans += 1
        return ans

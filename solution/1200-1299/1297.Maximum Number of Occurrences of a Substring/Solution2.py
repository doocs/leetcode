class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(
        self, s: Union[str, List[str]], base: int = 13331, mod: int = 998244353
    ):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def maxFreq(self, s: str, maxLetters: int, minSize: int, maxSize: int) -> int:
        freq = Counter()
        hashing = Hashing(s)
        cnt = Counter()
        ans = k = 0
        for i, c in enumerate(s, 1):
            freq[c] += 1
            if freq[c] == 1:
                k += 1
            if i >= minSize:
                if k <= maxLetters:
                    x = hashing.query(i - minSize + 1, i)
                    cnt[x] += 1
                    ans = max(ans, cnt[x])
                j = i - minSize
                freq[s[j]] -= 1
                if freq[s[j]] == 0:
                    k -= 1
        return ans

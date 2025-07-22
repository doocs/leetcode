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
    def partitionString(self, s: str) -> List[str]:
        hashing = Hashing(s)
        vis = set()
        l = 1
        ans = []
        for r, c in enumerate(s, 1):
            x = hashing.query(l, r)
            if x not in vis:
                vis.add(x)
                ans.append(s[l - 1 : r])
                l = r + 1
        return ans

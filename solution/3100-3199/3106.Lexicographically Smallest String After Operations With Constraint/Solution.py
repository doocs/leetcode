class Solution:
    def getSmallestString(self, s: str, k: int) -> str:
        cs = list(s)
        for i, c1 in enumerate(s):
            for c2 in ascii_lowercase:
                if c2 >= c1:
                    break
                d = min(ord(c1) - ord(c2), 26 - ord(c1) + ord(c2))
                if d <= k:
                    cs[i] = c2
                    k -= d
                    break
        return "".join(cs)

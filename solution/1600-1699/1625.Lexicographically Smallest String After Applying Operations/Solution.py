class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        ans = s
        n = len(s)
        s = list(s)
        for _ in range(n):
            s = s[-b:] + s[:-b]
            for j in range(10):
                for k in range(1, n, 2):
                    s[k] = str((int(s[k]) + a) % 10)
                if b & 1:
                    for p in range(10):
                        for k in range(0, n, 2):
                            s[k] = str((int(s[k]) + a) % 10)
                        t = ''.join(s)
                        if ans > t:
                            ans = t
                else:
                    t = ''.join(s)
                    if ans > t:
                        ans = t
        return ans

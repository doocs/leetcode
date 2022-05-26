class Solution:
    def countTriples(self, n: int) -> int:
        res = 0
        for a in range(1, n + 1):
            for b in range(1, n + 1):
                t = a**2 + b**2
                c = int(sqrt(t))
                if c <= n and c**2 == t:
                    res += 1
        return res

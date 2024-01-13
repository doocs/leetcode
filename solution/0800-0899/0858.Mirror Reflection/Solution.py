class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        g = gcd(p, q)
        p = (p // g) % 2
        q = (q // g) % 2
        if p == 1 and q == 1:
            return 1
        return 0 if p == 1 else 2

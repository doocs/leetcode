class Solution:
    def countTriples(self, n: int) -> int:
        ans = 0
        for a in range(1, n):
            for b in range(1, n):
                x = a * a + b * b
                c = int(sqrt(x))
                if c <= n and c * c == x:
                    ans += 1
        return ans

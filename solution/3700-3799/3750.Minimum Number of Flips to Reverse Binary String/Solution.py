class Solution:
    def minimumFlips(self, n: int) -> int:
        s = bin(n)[2:]
        m = len(s)
        return sum(s[i] != s[m - i - 1] for i in range(m // 2)) * 2

class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        a, b, c, d = 1, 0, 0, 0
        for _ in range(k):
            aa = ((n - 1) * b + (m - 1) * c) % mod
            bb = (a + (n - 2) * b + (m - 1) * d) % mod
            cc = (a + (m - 2) * c + (n - 1) * d) % mod
            dd = (b + c + (n - 2) * d + (m - 2) * d) % mod
            a, b, c, d = aa, bb, cc, dd
        if source[0] == dest[0]:
            return a if source[1] == dest[1] else c
        return b if source[1] == dest[1] else d

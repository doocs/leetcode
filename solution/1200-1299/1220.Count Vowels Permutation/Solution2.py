class Solution:
    def countVowelPermutation(self, n: int) -> int:
        mod = 10**9 + 7

        def mul(a: List[List[int]], b: List[List[int]]) -> List[List[int]]:
            m, n = len(a), len(b[0])
            c = [[0] * n for _ in range(m)]
            for i in range(m):
                for j in range(n):
                    for k in range(len(b)):
                        c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod
            return c

        def pow(a: List[List[int]], n: int) -> List[int]:
            res = [[1] * len(a)]
            while n:
                if n & 1:
                    res = mul(res, a)
                a = mul(a, a)
                n >>= 1
            return res

        a = [
            [0, 1, 0, 0, 0],
            [1, 0, 1, 0, 0],
            [1, 1, 0, 1, 1],
            [0, 0, 1, 0, 1],
            [1, 0, 0, 0, 0],
        ]
        res = pow(a, n - 1)
        return sum(map(sum, res)) % mod

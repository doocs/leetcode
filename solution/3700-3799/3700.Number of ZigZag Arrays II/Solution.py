class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        mod = 10**9 + 7
        m = r - l + 1
        size = 2 * m
        trans = [[0] * size for _ in range(size)]
        for x in range(m):
            for y in range(x):
                trans[y][m + x] = 1
        for x in range(m):
            for y in range(x + 1, m):
                trans[m + y][x] = 1

        def mul_mat(a, b):
            res = [[0] * size for _ in range(size)]
            for i in range(size):
                for k in range(size):
                    if a[i][k] == 0:
                        continue
                    aik = a[i][k]
                    for j in range(size):
                        if b[k][j]:
                            res[i][j] = (res[i][j] + aik * b[k][j]) % mod
            return res

        def mul_vec(mat, vec):
            res = [0] * size
            for i in range(size):
                s = 0
                for j in range(size):
                    s = (s + mat[i][j] * vec[j]) % mod
                res[i] = s
            return res

        power = [[int(i == j) for j in range(size)] for i in range(size)]
        exp = n - 1
        while exp:
            if exp & 1:
                power = mul_mat(power, trans)
            trans = mul_mat(trans, trans)
            exp >>= 1
        init = [1] * size
        return sum(mul_vec(power, init)) % mod

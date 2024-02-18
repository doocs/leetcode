class Solution:
    def mostFrequentPrime(self, mat: List[List[int]]) -> int:
        def is_prime(x: int) -> int:
            return all(x % i != 0 for i in range(2, isqrt(x) + 1))

        m, n = len(mat), len(mat[0])
        cnt = Counter()
        for i in range(m):
            for j in range(n):
                for a in range(-1, 2):
                    for b in range(-1, 2):
                        if a == 0 and b == 0:
                            continue
                        x, y, v = i + a, j + b, mat[i][j]
                        while 0 <= x < m and 0 <= y < n:
                            v = v * 10 + mat[x][y]
                            if is_prime(v):
                                cnt[v] += 1
                            x, y = x + a, y + b
        ans, mx = -1, 0
        for v, x in cnt.items():
            if mx < x:
                mx = x
                ans = v
            elif mx == x:
                ans = max(ans, v)
        return ans

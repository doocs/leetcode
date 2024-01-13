class Solution:
    def numOfWays(self, n: int) -> int:
        def f1(x: int) -> bool:
            last = -1
            for _ in range(3):
                if x % 3 == last:
                    return False
                last = x % 3
                x //= 3
            return True

        def f2(x: int, y: int) -> bool:
            for _ in range(3):
                if x % 3 == y % 3:
                    return False
                x //= 3
                y //= 3
            return True

        mod = 10**9 + 7
        m = 27
        valid = {i for i in range(m) if f1(i)}
        d = defaultdict(list)
        for i in valid:
            for j in valid:
                if f2(i, j):
                    d[i].append(j)
        f = [int(i in valid) for i in range(m)]
        for _ in range(n - 1):
            g = [0] * m
            for i in valid:
                for j in d[i]:
                    g[j] = (g[j] + f[i]) % mod
            f = g
        return sum(f) % mod

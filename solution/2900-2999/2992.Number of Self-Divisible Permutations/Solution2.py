class Solution:
    def selfDivisiblePermutationCount(self, n: int) -> int:
        f = [0] * (1 << n)
        f[0] = 1
        for mask in range(1 << n):
            i = mask.bit_count()
            for j in range(1, n + 1):
                if (mask >> (j - 1) & 1) == 1 and gcd(i, j) == 1:
                    f[mask] += f[mask ^ (1 << (j - 1))]
        return f[-1]

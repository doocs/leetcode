primes = []
x = 2
M = 1000
while len(primes) < M:
    is_prime = True
    for p in primes:
        if p * p > x:
            break
        if x % p == 0:
            is_prime = False
            break
    if is_prime:
        primes.append(x)
    x += 1


class Solution:
    def minNumberOfPrimes(self, n: int, m: int) -> int:
        min = lambda x, y: x if x < y else y
        f = [0] + [inf] * n
        for x in primes[:m]:
            for i in range(x, n + 1):
                f[i] = min(f[i], f[i - x] + 1)
        return f[n] if f[n] < inf else -1

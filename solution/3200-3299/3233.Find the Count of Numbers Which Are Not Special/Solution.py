m = 31623
primes = [True] * (m + 1)
primes[0] = primes[1] = False
for i in range(2, m + 1):
    if primes[i]:
        for j in range(i + i, m + 1, i):
            primes[j] = False


class Solution:
    def nonSpecialCount(self, l: int, r: int) -> int:
        lo = ceil(sqrt(l))
        hi = floor(sqrt(r))
        cnt = sum(primes[i] for i in range(lo, hi + 1))
        return r - l + 1 - cnt

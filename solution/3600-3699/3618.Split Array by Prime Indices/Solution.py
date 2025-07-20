m = 10**5 + 10
primes = [True] * m
primes[0] = primes[1] = False
for i in range(2, m):
    if primes[i]:
        for j in range(i + i, m, i):
            primes[j] = False


class Solution:
    def splitArray(self, nums: List[int]) -> int:
        return abs(sum(x if primes[i] else -x for i, x in enumerate(nums)))

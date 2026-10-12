mx = 10**6
primes = []
is_prime = [True] * (mx + 1)
is_prime[0] = is_prime[1] = False

for i in range(2, mx + 1):
    if is_prime[i]:
        primes.append(i)
        if i * i <= mx:
            for j in range(i * i, mx + 1, i):
                is_prime[j] = False


class Solution:
    def maxPrimes(self, n: int, s: int) -> list[int]:
        ans = []
        for p in primes:
            if p > n or p > s:
                break
            ans.append(p)
            s -= p
        return ans

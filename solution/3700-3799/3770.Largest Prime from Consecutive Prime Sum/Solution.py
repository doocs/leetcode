mx = 500000
is_prime = [True] * (mx + 1)
is_prime[0] = is_prime[1] = False
primes = []
for i in range(2, mx + 1):
    if is_prime[i]:
        primes.append(i)
        for j in range(i * i, mx + 1, i):
            is_prime[j] = False
s = [0]
t = 0
for x in primes:
    t += x
    if t > mx:
        break
    if is_prime[t]:
        s.append(t)


class Solution:
    def largestPrime(self, n: int) -> int:
        i = bisect_right(s, n) - 1
        return s[i]

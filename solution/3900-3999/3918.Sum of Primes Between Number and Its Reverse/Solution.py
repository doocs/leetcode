limit = 1000
is_prime = [True] * (limit + 1)
is_prime[0] = is_prime[1] = False
for i in range(2, int(limit**0.5) + 1):
    if is_prime[i]:
        for j in range(i * i, limit + 1, i):
            is_prime[j] = False


class Solution:
    def sumOfPrimesInRange(self, n: int) -> int:
        r = int(str(n)[::-1])
        low = min(n, r)
        high = max(n, r)
        return sum(x for x in range(low, high + 1) if is_prime[x])

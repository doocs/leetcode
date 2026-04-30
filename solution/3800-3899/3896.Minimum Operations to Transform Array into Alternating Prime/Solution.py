MX = 200000
is_prime = [True] * (MX + 1)
is_prime[0] = is_prime[1] = False

for i in range(2, int(MX**0.5) + 1):
    if is_prime[i]:
        for j in range(i * i, MX + 1, i):
            is_prime[j] = False

primes = [i for i in range(2, MX + 1) if is_prime[i]]


class Solution:
    def minOperations(self, nums: list[int]) -> int:
        ans = 0
        for i, x in enumerate(nums):
            if i % 2 == 0:
                j = bisect_left(primes, x)
                ans += primes[j] - x
            else:
                if is_prime[x]:
                    ans += 2 if x == 2 else 1
        return ans

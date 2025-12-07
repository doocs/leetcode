primes = []
for i in range(1 << 14):
    s = bin(i)[2:]
    if s == s[::-1]:
        primes.append(i)


class Solution:
    def minOperations(self, nums: List[int]) -> List[int]:
        ans = []
        for x in nums:
            i = bisect_left(primes, x)
            times = inf
            if i < len(primes):
                times = min(times, primes[i] - x)
            if i >= 1:
                times = min(times, x - primes[i - 1])
            ans.append(times)
        return ans

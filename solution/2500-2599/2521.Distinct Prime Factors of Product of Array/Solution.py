class Solution:
    def distinctPrimeFactors(self, nums: List[int]) -> int:
        s = set()
        for n in nums:
            i = 2
            while i <= n // i:
                if n % i == 0:
                    s.add(i)
                    while n % i == 0:
                        n //= i
                i += 1
            if n > 1:
                s.add(n)
        return len(s)

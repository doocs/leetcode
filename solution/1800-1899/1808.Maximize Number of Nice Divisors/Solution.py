class Solution:
    def maxNiceDivisors(self, primeFactors: int) -> int:
        mod = 10**9 + 7
        if primeFactors < 4:
            return primeFactors
        if primeFactors % 3 == 0:
            return pow(3, primeFactors // 3, mod) % mod
        if primeFactors % 3 == 1:
            return 4 * pow(3, primeFactors // 3 - 1, mod) % mod
        return 2 * pow(3, primeFactors // 3, mod) % mod

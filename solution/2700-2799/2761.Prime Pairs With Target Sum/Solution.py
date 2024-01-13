class Solution:
    def findPrimePairs(self, n: int) -> List[List[int]]:
        primes = [True] * n
        for i in range(2, n):
            if primes[i]:
                for j in range(i + i, n, i):
                    primes[j] = False
        ans = []
        for x in range(2, n // 2 + 1):
            y = n - x
            if primes[x] and primes[y]:
                ans.append([x, y])
        return ans

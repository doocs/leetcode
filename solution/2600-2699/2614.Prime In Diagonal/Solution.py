class Solution:
    def diagonalPrime(self, nums: List[List[int]]) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        n = len(nums)
        ans = 0
        for i, row in enumerate(nums):
            if is_prime(row[i]):
                ans = max(ans, row[i])
            if is_prime(row[n - i - 1]):
                ans = max(ans, row[n - i - 1])
        return ans

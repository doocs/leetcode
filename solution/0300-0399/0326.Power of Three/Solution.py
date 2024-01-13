class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        while n > 2:
            if n % 3:
                return False
            n //= 3
        return n == 1

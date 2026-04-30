class Solution:
    def validDigit(self, n: int, x: int) -> bool:
        has_x = False
        while n > 9:
            has_x = has_x or n % 10 == x
            n //= 10
        return has_x and n != x

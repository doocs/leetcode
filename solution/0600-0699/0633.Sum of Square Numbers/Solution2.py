class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        for i in range(2, int(sqrt(c)) + 1):
            if c % i == 0:
                exp = 0
                while c % i == 0:
                    c //= i
                    exp += 1
                if i % 4 == 3 and exp % 2 != 0:
                    return False
        return c % 4 != 3

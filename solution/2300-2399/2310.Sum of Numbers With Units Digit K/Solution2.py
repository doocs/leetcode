class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, 11):
            if (k * i) % 10 == num % 10 and k * i <= num:
                return i
        return -1

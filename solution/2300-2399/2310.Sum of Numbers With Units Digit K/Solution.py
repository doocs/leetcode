class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, num + 1):
            if (t := num - k * i) >= 0 and t % 10 == 0:
                return i
        return -1

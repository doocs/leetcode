class Solution:
    def sumOfThree(self, num: int) -> list[int]:
        x, mod = divmod(num, 3)
        return [] if mod else [x - 1, x, x + 1]

class Solution:
    def sumOfThree(self, num: int) -> List[int]:
        x, mod = divmod(num, 3)
        return [] if mod else [x - 1, x, x + 1]

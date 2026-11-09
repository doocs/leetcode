class Solution:
    def swapNumbers(self, numbers: list[int]) -> list[int]:
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers

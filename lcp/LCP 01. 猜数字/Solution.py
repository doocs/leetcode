class Solution:
    def game(self, guess: list[int], answer: list[int]) -> int:
        return sum(a == b for a, b in zip(guess, answer))

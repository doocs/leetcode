class Solution:
    def game(self, guess: List[int], answer: List[int]) -> int:
        return sum(a == b for a, b in zip(guess, answer))

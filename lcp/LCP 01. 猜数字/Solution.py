class Solution:
    def game(self, guess: List[int], answer: List[int]) -> int:
        return sum(1 for i in range(3) if guess[i] == answer[i])

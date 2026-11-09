class Solution:
    def masterMind(self, solution: str, guess: str) -> list[int]:
        x = sum(a == b for a, b in zip(solution, guess))
        y = sum((Counter(solution) & Counter(guess)).values())
        return [x, y - x]

class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        return all(abs(x - y) <= 2 for x, y in pairwise(map(int, list(s))))

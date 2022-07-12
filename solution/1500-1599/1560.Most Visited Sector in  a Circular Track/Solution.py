class Solution:
    def mostVisited(self, n: int, rounds: List[int]) -> List[int]:
        if rounds[0] <= rounds[-1]:
            return list(range(rounds[0], rounds[-1] + 1))
        return list(range(1, rounds[-1] + 1)) + list(range(rounds[0], n + 1))

class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        d = [0] * 26
        for i, c in enumerate(s):
            j = ord(c) - ord("a")
            if d[j] and i - d[j] != distance[j]:
                return False
            d[j] = i + 1
        return True

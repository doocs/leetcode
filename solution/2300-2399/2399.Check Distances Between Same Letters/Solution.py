class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        d = defaultdict(int)
        for i, c in enumerate(s, 1):
            if d[c] and i - d[c] - 1 != distance[ord(c) - ord('a')]:
                return False
            d[c] = i
        return True

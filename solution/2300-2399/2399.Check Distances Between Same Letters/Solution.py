class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        d = defaultdict(int)
        for i, c in enumerate(map(ord, s), 1):
            j = c - ord("a")
            if d[j] and i - d[j] - 1 != distance[j]:
                return False
            d[j] = i
        return True

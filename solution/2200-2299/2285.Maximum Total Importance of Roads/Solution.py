class Solution:
    def maximumImportance(self, n: int, roads: list[list[int]]) -> int:
        deg = [0] * n
        for a, b in roads:
            deg[a] += 1
            deg[b] += 1
        deg.sort()
        return sum(i * v for i, v in enumerate(deg, 1))

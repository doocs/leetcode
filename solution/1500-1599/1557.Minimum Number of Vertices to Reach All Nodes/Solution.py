class Solution:
    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        cnt = Counter(t for _, t in edges)
        return [i for i in range(n) if cnt[i] == 0]

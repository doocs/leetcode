class Solution:
    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        s = {to for _, to in edges}
        return [i for i in range(n) if i not in s]

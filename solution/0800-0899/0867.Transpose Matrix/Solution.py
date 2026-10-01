class Solution:
    def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
        return [list(row) for row in zip(*matrix)]

class Solution:
    def luckyNumbers (self, matrix: List[List[int]]) -> List[int]:
        row_min = {min(rows) for rows in matrix}
        col_max = {max(cols) for cols in zip(*matrix)}
        return [e for e in row_min if e in col_max]

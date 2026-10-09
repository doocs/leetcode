from typing import NamedTuple


class Tuple(NamedTuple):
    entry: int
    row_idx: int
    cumulated_count: int


class Solution:
    def numSubmat(self, mat: list[list[int]]) -> int:
        rows = len(mat)
        cols = len(mat[0])

        table = [[0] * cols for _ in range(rows)]

        # `stack[j]`: each jth column's remaining tuples:
        # (table entry, row idx, cumulated count until [i][j]).
        stack: list[list[Tuple]] = [[] for _ in range(cols)]

        submatrices_count = 0

        for row_idx in range(rows):
            for col_idx in range(cols):
                if mat[row_idx][col_idx] == 1:
                    table[row_idx][col_idx] = 1
                    if col_idx > 0:
                        table[row_idx][col_idx] += table[row_idx][col_idx - 1]

                current_entry = table[row_idx][col_idx]

                while stack[col_idx] and stack[col_idx][-1].entry >= current_entry:
                    stack[col_idx].pop(-1)

                cumulated_count = 0
                prev_row_idx = -1

                if stack[col_idx]:
                    prev_row_idx = stack[col_idx][-1].row_idx  # Previous barrier.

                    cumulated_count += stack[col_idx][
                        -1
                    ].cumulated_count  # Inheritance.

                cumulated_count += current_entry * (row_idx - prev_row_idx)
                submatrices_count += cumulated_count

                stack[col_idx].append(Tuple(current_entry, row_idx, cumulated_count))

        return submatrices_count

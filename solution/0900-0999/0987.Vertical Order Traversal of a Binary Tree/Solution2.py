# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> list[list[int]]:
        # Format: (tree node, row, column).
        queue: deque[tuple[TreeNode, int, int]] = deque([(root, 0, 0)])

        # Deque append left speeds up indexing.
        # Each tuple format: (row, value).
        columns_values: deque[list[tuple[int, int]]] = deque([[]])

        leftmost_col, rightmost_col = 0, 0

        while queue:
            node, row, column = queue.popleft()

            if column < leftmost_col:
                leftmost_col = column
                columns_values.appendleft([])

            if column > rightmost_col:
                rightmost_col = column
                columns_values.append([])

            columns_values[column - leftmost_col].append((row, node.val))

            if node.left:
                queue.append((node.left, row + 1, column - 1))
            if node.right:
                queue.append((node.right, row + 1, column + 1))

        vertical_traversal: list[list[int]] = []

        for column_values in columns_values:
            vertical_traversal.append([])

            column_values.sort()
            for _, value in column_values:
                vertical_traversal[-1].append(value)

        return vertical_traversal

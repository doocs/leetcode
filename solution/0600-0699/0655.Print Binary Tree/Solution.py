# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def printTree(self, root: Optional[TreeNode]) -> List[List[str]]:
        def height(root):
            if root is None:
                return -1
            return 1 + max(height(root.left), height(root.right))

        def dfs(root, r, c):
            if root is None:
                return
            ans[r][c] = str(root.val)
            dfs(root.left, r + 1, c - 2 ** (h - r - 1))
            dfs(root.right, r + 1, c + 2 ** (h - r - 1))

        h = height(root)
        m, n = h + 1, 2 ** (h + 1) - 1
        ans = [[""] * n for _ in range(m)]
        dfs(root, 0, (n - 1) // 2)
        return ans

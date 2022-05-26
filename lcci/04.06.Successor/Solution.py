# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, prev
            if prev == p:
                ans = root
            prev = root
            dfs(root.right)

        ans = prev = None
        dfs(root)
        return ans

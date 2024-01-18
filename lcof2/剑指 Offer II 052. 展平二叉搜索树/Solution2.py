# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def increasingBST(self, root: TreeNode) -> TreeNode:
        def dfs(root: TreeNode):
            if root is None:
                return

            dfs(root.left)

            nonlocal cur
            cur.right = root
            root.left = None
            cur = cur.right

            dfs(root.right)

        cur = dummy = TreeNode()
        dfs(root)
        return dummy.right

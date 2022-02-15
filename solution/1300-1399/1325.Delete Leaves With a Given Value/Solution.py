# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def removeLeafNodes(
        self, root: Optional[TreeNode], target: int
    ) -> Optional[TreeNode]:
        def dfs(root, prev):
            if root is None:
                return
            dfs(root.left, root)
            dfs(root.right, root)
            if root.left is None and root.right is None and root.val == target:
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None

        p = TreeNode(val=0, left=root)
        dfs(root, p)
        return p.left

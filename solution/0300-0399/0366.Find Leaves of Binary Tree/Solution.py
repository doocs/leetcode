# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findLeaves(self, root: TreeNode) -> List[List[int]]:
        def dfs(root, prev, t):
            if root is None:
                return
            if root.left is None and root.right is None:
                t.append(root.val)
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        res = []
        prev = TreeNode(left=root)
        while prev.left:
            t = []
            dfs(prev.left, prev, t)
            res.append(t)
        return res

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countUnivalSubtrees(self, root: TreeNode) -> int:
        if root is None:
            return 0
        cnt = 0

        def dfs(root):
            nonlocal cnt
            if root.left is None and root.right is None:
                cnt += 1
                return True
            res = True
            if root.left:
                # exec dfs(root.left) first
                res = dfs(root.left) and res and root.val == root.left.val
            if root.right:
                # exec dfs(root.right) first
                res = dfs(root.right) and res and root.val == root.right.val
            cnt += res
            return res

        dfs(root)
        return cnt

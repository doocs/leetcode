# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def lowestCommonAncestor(
        self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode'
    ) -> 'TreeNode':
        def dfs(root, p, q):
            if root is None:
                return False
            l = dfs(root.left, p, q)
            r = dfs(root.right, p, q)
            nonlocal ans
            if l and r:
                ans = root
            if (l or r) and (root.val == p.val or root.val == q.val):
                ans = root
            return l or r or root.val == p.val or root.val == q.val

        ans = None
        dfs(root, p, q)
        return ans

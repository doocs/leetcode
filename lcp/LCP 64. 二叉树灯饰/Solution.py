# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def closeLampInTree(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0, 0, 0, 0
            l1, l2, l3, l4 = dfs(root.left)
            r1, r2, r3, r4 = dfs(root.right)
            t1 = t2 = t3 = t4 = inf
            if root.val:
                t1 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
                t2 = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
                t3 = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
                t4 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
            else:
                t1 = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
                t2 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
                t3 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
                t4 = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
            return t1, t2, t3, t4

        return dfs(root)[0]

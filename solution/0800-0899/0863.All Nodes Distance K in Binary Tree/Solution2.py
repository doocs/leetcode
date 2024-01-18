# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def dfs1(root, fa):
            if root is None:
                return
            p[root] = fa
            dfs1(root.left, root)
            dfs1(root.right, root)

        def dfs2(root, fa, k):
            if root is None:
                return
            if k == 0:
                ans.append(root.val)
                return
            for nxt in (root.left, root.right, p[root]):
                if nxt != fa:
                    dfs2(nxt, root, k - 1)

        p = {}
        dfs1(root, None)
        ans = []
        dfs2(target, None, k)
        return ans

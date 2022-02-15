# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def parents(root, prev):
            nonlocal p
            if root is None:
                return
            p[root] = prev
            parents(root.left, root)
            parents(root.right, root)

        def dfs(root, k):
            nonlocal ans, vis
            if root is None or root.val in vis:
                return
            vis.add(root.val)
            if k == 0:
                ans.append(root.val)
                return
            dfs(root.left, k - 1)
            dfs(root.right, k - 1)
            dfs(p[root], k - 1)

        p = {}
        parents(root, None)
        ans = []
        vis = set()
        dfs(target, k)
        return ans

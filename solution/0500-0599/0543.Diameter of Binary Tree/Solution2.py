# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        def build(root):
            if root is None:
                return
            nonlocal d
            if root.left:
                d[root].add(root.left)
                d[root.left].add(root)
            if root.right:
                d[root].add(root.right)
                d[root.right].add(root)
            build(root.left)
            build(root.right)

        def dfs(u, t):
            nonlocal ans, vis, d, next
            if u in vis:
                return
            vis.add(u)
            if t > ans:
                ans = t
                next = u
            for v in d[u]:
                dfs(v, t + 1)

        d = defaultdict(set)
        ans = 0
        next = root
        build(root)
        vis = set()
        dfs(next, 0)
        vis.clear()
        dfs(next, 0)
        return ans

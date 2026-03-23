# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxSum(self, root: Optional[TreeNode]) -> int:
        def dfs(node, p):
            if node is None:
                return
            g[node].append(p)
            g[node].append(node.left)
            g[node].append(node.right)
            dfs(node.left, node)
            dfs(node.right, node)

        def dfs2(node):
            if node is None or node.val in vis:
                return 0
            vis.add(node.val)
            res = node.val
            best = 0
            for nxt in g[node]:
                best = max(best, dfs2(nxt))
            vis.remove(node.val)
            res += best
            return res

        g = defaultdict(list)
        dfs(root, None)
        vis = set()
        ans = -inf
        for node in g:
            ans = max(ans, dfs2(node))
            vis.clear()
        return ans

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        def dfs(root):
            if root is None:
                return
            if root.left:
                g[root.val].append(root.left.val)
                g[root.left.val].append(root.val)
            if root.right:
                g[root.val].append(root.right.val)
                g[root.right.val].append(root.val)
            dfs(root.left)
            dfs(root.right)

        def dfs2(i, fa):
            ans = 0
            for j in g[i]:
                if j != fa:
                    ans = max(ans, 1 + dfs2(j, i))
            return ans

        g = defaultdict(list)
        dfs(root)
        return dfs2(start, -1)

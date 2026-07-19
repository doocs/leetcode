# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countDominantNodes(self, root: TreeNode | None) -> int:
        def dfs(node: TreeNode | None) -> int:
            if node is None:
                return -inf
            l = dfs(node.left)
            r = dfs(node.right)
            mx = max(l, r, node.val)
            if mx == node.val:
                nonlocal ans
                ans += 1
            return mx

        ans = 0
        dfs(root)
        return ans

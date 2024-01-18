# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxLevelSum(self, root: Optional[TreeNode]) -> int:
        def dfs(node, i):
            if node is None:
                return
            if i == len(s):
                s.append(node.val)
            else:
                s[i] += node.val
            dfs(node.left, i + 1)
            dfs(node.right, i + 1)

        s = []
        dfs(root, 0)
        return s.index(max(s)) + 1

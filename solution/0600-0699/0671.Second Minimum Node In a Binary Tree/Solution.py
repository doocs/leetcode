# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        def dfs(root, val):
            if root:
                dfs(root.left, val)
                dfs(root.right, val)
                nonlocal ans
                if root.val > val:
                    ans = root.val if ans == -1 else min(ans, root.val)

        ans = -1
        dfs(root, root.val)
        return ans

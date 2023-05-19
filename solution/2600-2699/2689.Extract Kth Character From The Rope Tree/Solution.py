# Definition for a rope tree node.
# class RopeTreeNode(object):
#     def __init__(self, len=0, val="", left=None, right=None):
#         self.len = len
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getKthCharacter(self, root: Optional[object], k: int) -> str:
        def dfs(root):
            if root is None:
                return ""
            if root.len == 0:
                return root.val
            return dfs(root.left) + dfs(root.right)

        return dfs(root)[k - 1]

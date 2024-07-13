# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        ans, diff = root.val, inf
        while root:
            nxt = abs(root.val - target)
            if nxt < diff or (nxt == diff and root.val < ans):
                diff = nxt
                ans = root.val
            root = root.left if target < root.val else root.right
        return ans

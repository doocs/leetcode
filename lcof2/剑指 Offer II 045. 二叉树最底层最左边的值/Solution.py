# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findBottomLeftValue(self, root: TreeNode) -> int:
        d = deque([root])
        ans = -1
        while d:
            n = len(d)
            for i in range(n):
                node = d.popleft()
                if i == 0:
                    ans = node.val
                if node.left:
                    d.append(node.left)
                if node.right:
                    d.append(node.right)
        return ans

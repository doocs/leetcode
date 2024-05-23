# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minimumLevel(self, root: Optional[TreeNode]) -> int:
        q = deque([root])
        ans = 0
        level, s = 1, inf
        while q:
            t = 0
            for _ in range(len(q)):
                node = q.popleft()
                t += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if s > t:
                s = t
                ans = level
            level += 1
        return ans

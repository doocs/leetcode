# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isEvenOddTree(self, root: Optional[TreeNode]) -> bool:
        even = 1
        q = deque([root])
        while q:
            prev = 0 if even else inf
            for _ in range(len(q)):
                root = q.popleft()
                if even and (root.val % 2 == 0 or prev >= root.val):
                    return False
                if not even and (root.val % 2 == 1 or prev <= root.val):
                    return False
                prev = root.val
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
            even ^= 1
        return True

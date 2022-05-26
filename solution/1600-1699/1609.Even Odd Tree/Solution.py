# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isEvenOddTree(self, root: TreeNode) -> bool:
        even = True
        q = deque([root])
        while q:
            n = len(q)
            prev = 0 if even else 10**6
            for _ in range(n):
                node = q.popleft()
                if even and (prev >= node.val or node.val % 2 == 0):
                    return False
                if not even and (prev <= node.val or node.val % 2 == 1):
                    return False
                prev = node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            even = not even
        return True

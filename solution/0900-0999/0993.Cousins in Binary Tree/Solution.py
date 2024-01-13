# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        q = deque([(root, None)])
        d = 0
        p1 = p2 = None
        d1 = d2 = 0
        while q:
            for _ in range(len(q)):
                node, fa = q.popleft()
                if node.val == x:
                    p1, d1 = fa, d
                if node.val == y:
                    p2, d2 = fa, d
                if node.left:
                    q.append((node.left, node))
                if node.right:
                    q.append((node.right, node))
            d += 1
        return p1 != p2 and d1 == d2

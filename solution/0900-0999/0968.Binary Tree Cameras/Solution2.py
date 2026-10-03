# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        stk = [(root, 0)]
        sub = {}
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.right, 0))
                stk.append((node.left, 0))
                continue
            la, lb, lc = sub[id(node.left)] if node.left is not None else (inf, 0, 0)
            ra, rb, rc = sub[id(node.right)] if node.right is not None else (inf, 0, 0)
            a = min(la, lb, lc) + min(ra, rb, rc) + 1
            b = min(la + rb, lb + ra, la + ra)
            c = lb + rb
            sub[id(node)] = (a, b, c)
        a, b, _ = sub[id(root)]
        return min(a, b)

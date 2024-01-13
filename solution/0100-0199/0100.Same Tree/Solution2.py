# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if p == q:
            return True
        if p is None or q is None:
            return False
        q1, q2 = deque([p]), deque([q])
        while q1 and q2:
            a, b = q1.popleft(), q2.popleft()
            if a.val != b.val:
                return False
            la, ra = a.left, a.right
            lb, rb = b.left, b.right
            if (la and not lb) or (lb and not la):
                return False
            if (ra and not rb) or (rb and not ra):
                return False
            if la:
                q1.append(la)
                q2.append(lb)
            if ra:
                q1.append(ra)
                q2.append(rb)
        return True

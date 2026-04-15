# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def zigzagLevelSum(self, root: TreeNode | None) -> list[int]:
        q = [root]
        ans = []
        left = True
        while q:
            nq = []
            for node in q:
                if node.left:
                    nq.append(node.left)
                if node.right:
                    nq.append(node.right)
            m = len(q)
            s = 0
            for i in range(m):
                node = q[i] if left else q[m - i - 1]
                child = node.left if left else node.right
                if not child:
                    break
                s += node.val
            ans.append(s)
            left = not left
            q = nq
        return ans

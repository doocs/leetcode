# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def equalToDescendants(self, root: Optional[TreeNode]) -> int:
        ans = 0
        stk = [(root, 0)]
        sub = {}
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                if node.right is not None:
                    stk.append((node.right, 0))
                if node.left is not None:
                    stk.append((node.left, 0))
                continue
            l = sub[id(node.left)] if node.left is not None else 0
            r = sub[id(node.right)] if node.right is not None else 0
            if l + r == node.val:
                ans += 1
            sub[id(node)] = node.val + l + r
        return ans

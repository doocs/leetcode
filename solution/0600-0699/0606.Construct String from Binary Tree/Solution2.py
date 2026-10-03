# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def tree2str(self, root: Optional[TreeNode]) -> str:
        parts = []
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if state == 0:
                if node is None:
                    continue
                parts.append(str(node.val))
                if node.left is None and node.right is None:
                    continue
                parts.append('(')
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            if state == 1:
                parts.append(')')
                if node.right is not None:
                    parts.append('(')
                    stk.append((node, 2))
                    stk.append((node.right, 0))
                continue
            parts.append(')')
        return ''.join(parts)

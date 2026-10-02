# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        ans = 0
        if root is None:
            return ans
        stack = [(root, False)]
        univalue = {}
        while stack:
            node, visited = stack.pop()
            if not visited:
                stack.append((node, True))
                if node.right:
                    stack.append((node.right, False))
                if node.left:
                    stack.append((node.left, False))
                continue
            left_ok = node.left is None or univalue[id(node.left)]
            right_ok = node.right is None or univalue[id(node.right)]
            is_univalue = (
                left_ok
                and right_ok
                and (node.left is None or node.left.val == node.val)
                and (node.right is None or node.right.val == node.val)
            )
            if is_univalue:
                ans += 1
            univalue[id(node)] = is_univalue
        return ans

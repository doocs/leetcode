# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        ans = []
        if root is None:
            return ans
        path = []
        stack = [(root, targetSum, False)]
        while stack:
            node, remaining, exiting = stack.pop()
            if exiting:
                path.pop()
                continue
            path.append(node.val)
            if node.left is None and node.right is None and remaining == node.val:
                ans.append(path[:])
            stack.append((node, remaining, True))
            if node.right:
                stack.append((node.right, remaining - node.val, False))
            if node.left:
                stack.append((node.left, remaining - node.val, False))
        return ans

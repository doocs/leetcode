# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> List[List[int]]:
        res, path = [], []

        def dfs(root, sum):
            if not root:
                return
            path.append(root.val)
            target = sum - root.val
            if target == 0 and not (root.left or root.right):
                res.append(list(path))
            dfs(root.left, target)
            dfs(root.right, target)
            path.pop()

        dfs(root, sum)
        return res

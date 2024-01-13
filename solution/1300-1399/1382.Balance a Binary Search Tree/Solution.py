# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        def dfs(root: TreeNode):
            if root is None:
                return
            dfs(root.left)
            nums.append(root.val)
            dfs(root.right)

        def build(i: int, j: int) -> TreeNode:
            if i > j:
                return None
            mid = (i + j) >> 1
            left = build(i, mid - 1)
            right = build(mid + 1, j)
            return TreeNode(nums[mid], left, right)

        nums = []
        dfs(root)
        return build(0, len(nums) - 1)

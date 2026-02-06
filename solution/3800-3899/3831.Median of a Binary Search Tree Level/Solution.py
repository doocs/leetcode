# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelMedian(self, root: Optional[TreeNode], level: int) -> int:
        def dfs(root: Optional[TreeNode], i: int):
            if root is None:
                return
            dfs(root.left, i + 1)
            if i == level:
                nums.append(root.val)
            dfs(root.right, i + 1)

        nums = []
        dfs(root, 0)
        return nums[len(nums) // 2] if nums else -1

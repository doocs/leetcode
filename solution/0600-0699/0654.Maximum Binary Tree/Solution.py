# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> TreeNode:
        def inner(nums, l, r):
            if l > r:
                return None
            mx = l
            for i in range(l + 1, r + 1):
                if nums[mx] < nums[i]:
                    mx = i
            return TreeNode(nums[mx], inner(nums, l, mx - 1), inner(nums, mx + 1, r))
        
        return inner(nums, 0, len(nums) - 1)

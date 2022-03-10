# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        def dfs(i, j):
            if i > j:
                return None
            if i == j:
                return TreeNode(nums[i])
            mid = (i + j) >> 1
            node = TreeNode(nums[mid])
            node.left = dfs(i, mid - 1)
            node.right = dfs(mid + 1, j)
            return node

        return dfs(0, len(nums) - 1)

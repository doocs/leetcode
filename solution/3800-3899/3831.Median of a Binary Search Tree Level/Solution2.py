# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelMedian(self, root: Optional[TreeNode], level: int) -> int:
        nums = []
        stk = [(root, 0, 0)]
        while stk:
            node, i, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, i, 1))
                stk.append((node.left, i + 1, 0))
                continue
            if i == level:
                nums.append(node.val)
            stk.append((node.right, i + 1, 0))
        return nums[len(nums) // 2] if nums else -1

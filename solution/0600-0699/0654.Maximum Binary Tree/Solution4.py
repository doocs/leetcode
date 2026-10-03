# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        n = len(nums)
        root = None
        stk = [(0, n, None, 0)]
        while stk:
            l, r, parent, side = stk.pop()
            if l >= r:
                continue
            i = l
            for j in range(l + 1, r):
                if nums[j] > nums[i]:
                    i = j
            node = TreeNode(nums[i])
            if parent is None:
                root = node
            elif side == 0:
                parent.left = node
            else:
                parent.right = node
            stk.append((i + 1, r, node, 1))
            stk.append((l, i, node, 0))
        return root

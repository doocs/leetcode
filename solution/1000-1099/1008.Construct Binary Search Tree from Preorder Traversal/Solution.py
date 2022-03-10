# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        def dfs(preorder):
            if not preorder:
                return None
            root = TreeNode(preorder[0])
            left, right = 1, len(preorder)
            while left < right:
                mid = (left + right) >> 1
                if preorder[mid] > preorder[0]:
                    right = mid
                else:
                    left = mid + 1
            root.left = dfs(preorder[1:left])
            root.right = dfs(preorder[left:])
            return root

        return dfs(preorder)

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        def dfs(i, j, n):
            if n <= 0:
                return None
            v = preorder[i]
            k = d[v]
            root = TreeNode(v)
            root.left = dfs(i + 1, j, k - j)
            root.right = dfs(i + 1 + k - j, k + 1, n - k + j - 1)
            return root

        d = {v: i for i, v in enumerate(inorder)}
        return dfs(0, 0, len(preorder))

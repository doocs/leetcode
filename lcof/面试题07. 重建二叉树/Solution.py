# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def dfs(i, j, n):
            if n < 1:
                return None
            root = TreeNode(preorder[i])
            k = d[preorder[i]]
            l = k - j
            root.left = dfs(i + 1, j, l)
            root.right = dfs(i + 1 + l, k + 1, n - l - 1)
            return root

        d = {v: i for i, v in enumerate(inorder)}
        return dfs(0, 0, len(preorder))

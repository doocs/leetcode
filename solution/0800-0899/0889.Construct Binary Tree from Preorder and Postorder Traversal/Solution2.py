# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructFromPrePost(
        self, preorder: List[int], postorder: List[int]
    ) -> Optional[TreeNode]:
        def dfs(i: int, j: int, n: int) -> Optional[TreeNode]:
            if n <= 0:
                return None
            root = TreeNode(preorder[i])
            if n == 1:
                return root
            k = pos[preorder[i + 1]]
            m = k - j + 1
            root.left = dfs(i + 1, j, m)
            root.right = dfs(i + m + 1, k + 1, n - m - 1)
            return root

        pos = {x: i for i, x in enumerate(postorder)}
        return dfs(0, 0, len(preorder))

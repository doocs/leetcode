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
        def dfs(a: int, b: int, c: int, d: int) -> Optional[TreeNode]:
            if a > b:
                return None
            root = TreeNode(preorder[a])
            if a == b:
                return root
            i = pos[preorder[a + 1]]
            m = i - c + 1
            root.left = dfs(a + 1, a + m, c, i)
            root.right = dfs(a + m + 1, b, i + 1, d - 1)
            return root

        pos = {x: i for i, x in enumerate(postorder)}
        return dfs(0, len(preorder) - 1, 0, len(postorder) - 1)

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        def dfs(root: Optional[TreeNode]):
            if root is None:
                return
            t.append(str(root.val))
            if root.left is None and root.right is None:
                ans.append("->".join(t))
            else:
                dfs(root.left)
                dfs(root.right)
            t.pop()

        ans = []
        t = []
        dfs(root)
        return ans

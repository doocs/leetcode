# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def delNodes(self, root: TreeNode, to_delete: List[int]) -> List[TreeNode]:
        def dfs(fa, root):
            if root is None:
                return
            dfs(root, root.left)
            dfs(root, root.right)
            if root.val in s:
                if fa and fa.left == root:
                    fa.left = None
                if fa and fa.right == root:
                    fa.right = None
                if root.left:
                    ans.append(root.left)
                if root.right:
                    ans.append(root.right)

        s = set(to_delete)
        ans = []
        if root.val not in s:
            ans.append(root)
        dfs(None, root)
        return ans

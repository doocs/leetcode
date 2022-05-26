# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        def dfs(root, t, s):
            if root is None:
                return
            t.append(root.val)
            s += root.val
            if root.left is None and root.right is None:
                if s == targetSum:
                    ans.append(t[:])
            dfs(root.left, t, s)
            dfs(root.right, t, s)
            t.pop()

        ans = []
        if root is None:
            return ans
        dfs(root, [], 0)
        return ans

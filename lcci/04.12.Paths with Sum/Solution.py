# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], sum: int) -> int:
        def dfs(root: Optional[TreeNode], s: int) -> int:
            if root is None:
                return 0
            s += root.val
            ans = cnt[s - sum]
            cnt[s] += 1
            ans += dfs(root.left, s)
            ans += dfs(root.right, s)
            cnt[s] -= 1
            return ans

        cnt = Counter({0: 1})
        return dfs(root, 0)

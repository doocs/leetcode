# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> int:
        def dfs(root: TreeNode, s: int):
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

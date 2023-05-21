# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def maxValue(self, root: TreeNode, k: int) -> int:
        def dfs(root: TreeNode) -> List[int]:
            ans = [0] * (k + 1)
            if root is None:
                return ans
            l, r = dfs(root.left), dfs(root.right)
            ans[0] = max(l) + max(r)
            for i in range(k):
                for j in range(k - i):
                    ans[i + j + 1] = max(ans[i + j + 1], l[i] + r[j] + root.val)
            return ans

        return max(dfs(root))

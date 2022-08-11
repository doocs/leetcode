# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        @cache
        def dfs(n):
            if n == 1:
                return [TreeNode()]
            res = []
            if n % 2:
                for i in range(n - 1):
                    j = n - i - 1
                    for left in dfs(i):
                        for right in dfs(j):
                            res.append(TreeNode(0, left, right))
            return res

        return dfs(n)

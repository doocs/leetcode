# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pseudoPalindromicPaths(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return
            nonlocal ans, counter
            counter[root.val] += 1
            if root.left is None and root.right is None:
                if sum(1 for i in range(1, 10) if counter[i] % 2 == 1) < 2:
                    ans += 1
            else:
                dfs(root.left)
                dfs(root.right)
            counter[root.val] -= 1

        ans = 0
        counter = [0] * 10
        dfs(root)
        return ans

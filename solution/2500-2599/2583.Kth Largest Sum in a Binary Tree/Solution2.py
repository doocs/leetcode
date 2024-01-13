# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        def dfs(root, d):
            if root is None:
                return
            if len(arr) <= d:
                arr.append(0)
            arr[d] += root.val
            dfs(root.left, d + 1)
            dfs(root.right, d + 1)

        arr = []
        dfs(root, 0)
        return -1 if len(arr) < k else nlargest(k, arr)[-1]

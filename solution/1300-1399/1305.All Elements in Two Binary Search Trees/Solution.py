# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getAllElements(
        self, root1: Optional[TreeNode], root2: Optional[TreeNode]
    ) -> List[int]:
        def dfs(root: Optional[TreeNode], nums: List[int]) -> int:
            if root is None:
                return
            dfs(root.left, nums)
            nums.append(root.val)
            dfs(root.right, nums)

        a, b = [], []
        dfs(root1, a)
        dfs(root2, b)
        m, n = len(a), len(b)
        i = j = 0
        ans = []
        while i < m and j < n:
            if a[i] <= b[j]:
                ans.append(a[i])
                i += 1
            else:
                ans.append(b[j])
                j += 1
        while i < m:
            ans.append(a[i])
            i += 1
        while j < n:
            ans.append(b[j])
            j += 1
        return ans

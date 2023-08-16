# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from sortedcontainers import SortedList


class Solution:
    def getNumber(self, root: Optional[TreeNode], ops: List[List[int]]) -> int:
        def dfs(root):
            if root is None:
                return
            sl.add(root.val)
            dfs(root.left)
            dfs(root.right)

        sl = SortedList()
        dfs(root)
        ans = 0
        for t, x, y in ops[::-1]:
            i = sl.bisect_left(x)
            while i < len(sl) and sl[i] <= y:
                sl.pop(i)
                ans += t == 1
        return ans

"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def levelOrder(self, root: 'Node') -> list[list[int]]:
        def dfs(root, i):
            if root is None:
                return
            if len(ans) <= i:
                ans.append([])
            ans[i].append(root.val)
            for child in root.children:
                dfs(child, i + 1)

        ans = []
        dfs(root, 0)
        return ans

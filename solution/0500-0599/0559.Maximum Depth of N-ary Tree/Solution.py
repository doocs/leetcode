"""
# Definition for a Node.
class Node:
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""


class Solution:
    def maxDepth(self, root: "Node") -> int:
        if root is None:
            return 0
        mx = 0
        for child in root.children:
            mx = max(mx, self.maxDepth(child))
        return 1 + mx

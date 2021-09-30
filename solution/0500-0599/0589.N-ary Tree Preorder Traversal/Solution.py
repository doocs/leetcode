"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def preorder(self, root: 'Node') -> List[int]:
        if not root:
            return []

        def PO(root):
            res.append(root.val)
            for i in root.children:
                PO(i)
        res = []
        PO(root)
        return res

"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def postorder(self, root: 'Node') -> List[int]:
        if not root:
            return []

        def PO(root):
            if root == None:
                return res
            else:
                for i in root.children:
                    PO(i)
                    res.append(i.val)
        res = []
        PO(root)
        res.append(root.val)
        return res

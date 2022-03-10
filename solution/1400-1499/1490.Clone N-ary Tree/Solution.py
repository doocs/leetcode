"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def cloneTree(self, root: 'Node') -> 'Node':
        if root:
            node = Node(val=root.val)
            node.children = [self.cloneTree(child) for child in root.children]
            return node

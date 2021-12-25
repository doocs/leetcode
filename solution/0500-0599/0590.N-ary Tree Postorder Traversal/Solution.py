"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def postorder(self, root: 'Node') -> List[int]:
        if root is None:
            return []
        stk = [root]
        ans = []
        while stk:
            node = stk.pop()
            ans.append(node.val)
            if node.children:
                for child in node.children:
                    stk.append(child)
        return ans[::-1]

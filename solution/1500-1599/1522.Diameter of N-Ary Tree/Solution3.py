"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def diameter(self, root: 'Node') -> int:
        """
        :type root: 'Node'
        :rtype: int
        """
        if root is None:
            return 0
        ans = 0
        height = {}
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if state == 0:
                stk.append((node, 1))
                for child in reversed(node.children):
                    if child is not None:
                        stk.append((child, 0))
            else:
                m1 = m2 = 0
                for child in node.children:
                    t = height.get(child, 0)
                    if t > m1:
                        m2, m1 = m1, t
                    elif t > m2:
                        m2 = t
                ans = max(ans, m1 + m2)
                height[node] = m1 + 1
        return ans

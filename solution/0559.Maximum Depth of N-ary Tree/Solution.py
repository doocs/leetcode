

"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""
# Performance
'''
Runtime: 36 ms, faster than 99.57% of Python3 online submissions for Maximum Depth of N-ary Tree.
Memory Usage: 14.5 MB, less than 100.00% of Python3 online submissions for Maximum Depth of N-ary Tree.
'''
class Solution:
    def maxDepth(self, root: 'Node') -> int:
        if not root:
            return 0
        max_depth = 1
        for child in root.children:
            max_depth = max(self.maxDepth(child) + 1 , max_depth)
        #print(max_depth, root.val)
        return max_depth
    
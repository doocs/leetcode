"""
# Definition for a Node.
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
"""


class Solution:
    def treeToDoublyList(self, root: "Node") -> "Node":
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal head, pre
            if pre:
                pre.right = root
            else:
                head = root
            root.left = pre
            pre = root
            dfs(root.right)

        if root is None:
            return None
        head = pre = None
        dfs(root)
        head.left = pre
        pre.right = head
        return head

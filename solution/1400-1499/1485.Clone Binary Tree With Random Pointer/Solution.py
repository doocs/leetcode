# Definition for Node.
# class Node:
#     def __init__(self, val=0, left=None, right=None, random=None):
#         self.val = val
#         self.left = left
#         self.right = right
#         self.random = random


class Solution:
    def copyRandomBinaryTree(self, root: "Optional[Node]") -> "Optional[NodeCopy]":
        def dfs(root: Optional[Node]) -> Optional[NodeCopy]:
            if root is None:
                return None
            if root in seen:
                return seen[root]
            copy = NodeCopy(root.val)
            seen[root] = copy
            copy.left = dfs(root.left)
            copy.right = dfs(root.right)
            copy.random = dfs(root.random)
            return copy

        seen = {}
        return dfs(root)

"""
# Definition for a Node.
class Node:
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""

"""
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
"""


class Codec:
    # Encodes an n-ary tree to a binary tree.
    def encode(self, root: "Optional[Node]") -> Optional[TreeNode]:
        if root is None:
            return None
        node = TreeNode(root.val)
        if not root.children:
            return node
        left = self.encode(root.children[0])
        node.left = left
        for child in root.children[1:]:
            left.right = self.encode(child)
            left = left.right
        return node

    # Decodes your binary tree to an n-ary tree.
    def decode(self, data: Optional[TreeNode]) -> "Optional[Node]":
        if data is None:
            return None
        node = Node(data.val, [])
        if data.left is None:
            return node
        left = data.left
        while left:
            node.children.append(self.decode(left))
            left = left.right
        return node


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(root))

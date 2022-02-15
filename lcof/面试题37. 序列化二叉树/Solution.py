# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return '[]'
        queue = deque()
        queue.append(root)
        res = []
        while queue:
            node = queue.popleft()
            if node:
                res.append(str(node.val))
                queue.append(node.left)
                queue.append(node.right)
            else:
                res.append('null')
        return f'[{",".join(res)}]'

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if not data or data == '[]':
            return None
        queue = deque()
        nodes = data[1:-1].split(',')
        root = TreeNode(nodes[0])
        queue.append(root)
        idx = 1
        while queue and idx < len(nodes):
            node = queue.popleft()
            if nodes[idx] != 'null':
                node.left = TreeNode(nodes[idx])
                queue.append(node.left)
            idx += 1
            if nodes[idx] != 'null':
                node.right = TreeNode(nodes[idx])
                queue.append(node.right)
            idx += 1
        return root


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))

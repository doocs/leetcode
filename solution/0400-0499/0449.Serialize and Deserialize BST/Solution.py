# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root: TreeNode) -> str:
        """Encodes a tree to a single string."""

        def dfs(root):
            if root is None:
                return
            nonlocal t
            t.append(str(root.val))
            t.append(',')
            dfs(root.left)
            dfs(root.right)

        if root is None:
            return ''
        t = []
        dfs(root)
        return ''.join(t[:-1])

    def deserialize(self, data: str) -> TreeNode:
        """Decodes your encoded data to tree."""

        def build(s, l, r):
            if l > r:
                return None
            root = TreeNode(int(s[l]))
            idx = r + 1
            for i in range(l + 1, r + 1):
                if int(s[i]) > root.val:
                    idx = i
                    break
            root.left = build(s, l + 1, idx - 1)
            root.right = build(s, idx, r)
            return root

        if not data:
            return None
        s = data.split(',')
        return build(s, 0, len(s) - 1)


# Your Codec object will be instantiated and called as such:
# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# tree = ser.serialize(root)
# ans = deser.deserialize(tree)
# return ans

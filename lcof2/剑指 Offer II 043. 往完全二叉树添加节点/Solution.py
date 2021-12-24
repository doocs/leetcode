# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class CBTInserter:

    def __init__(self, root: TreeNode):
        self.tree = []
        q = deque([root])
        while q:
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                self.tree.append(node)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

    def insert(self, val: int) -> int:
        pidx = (len(self.tree) - 1) >> 1
        node = TreeNode(val=val)
        self.tree.append(node)
        if self.tree[pidx].left is None:
            self.tree[pidx].left = node
        else:
            self.tree[pidx].right = node
        return self.tree[pidx].val

    def get_root(self) -> TreeNode:
        return self.tree[0]


# Your CBTInserter object will be instantiated and called as such:
# obj = CBTInserter(root)
# param_1 = obj.insert(val)
# param_2 = obj.get_root()

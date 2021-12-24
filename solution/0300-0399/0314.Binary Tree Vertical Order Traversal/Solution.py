# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = deque([(root, 0)])
        offset_vals = defaultdict(list)
        while q:
            node, offset = q.popleft()
            offset_vals[offset].append(node.val)
            if node.left:
                q.append((node.left, offset - 1))
            if node.right:
                q.append((node.right, offset + 1))
        res = []
        for _, vals in sorted(offset_vals.items(), key=lambda x: x[0]):
            res.append(vals)
        return res

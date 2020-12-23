# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> List[List[int]]:
        res = []
        q = deque()
        q.append((root, [], 0))
        while q:
            node, path, s = q.popleft()
            if node is None:
                continue
            if node.left is None and node.right is None:
                if s + node.val == sum:
                    res.append(path + [node.val])
                continue
            q.append((node.left, path + [node.val], s + node.val))
            q.append((node.right, path + [node.val], s + node.val))
        return res

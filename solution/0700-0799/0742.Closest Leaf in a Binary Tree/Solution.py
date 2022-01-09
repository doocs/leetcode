# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findClosestLeaf(self, root: TreeNode, k: int) -> int:
        def dfs(root, p):
            if root:
                g[root].append(p)
                g[p].append(root)
                dfs(root.left, root)
                dfs(root.right, root)

        g = defaultdict(list)
        dfs(root, None)
        q = deque([node for node in g if node and node.val == k])
        seen = set()
        while q:
            node = q.popleft()
            seen.add(node)
            if node:
                if node.left is None and node.right is None:
                    return node.val
                for next in g[node]:
                    if next not in seen:
                        q.append(next)

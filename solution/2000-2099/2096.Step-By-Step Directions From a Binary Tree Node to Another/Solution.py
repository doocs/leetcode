# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        edges = defaultdict(list)
        ans = None
        visited = set()

        def traverse(root):
            if not root:
                return
            if root.left:
                edges[root.val].append([root.left.val, 'L'])
                edges[root.left.val].append([root.val, 'U'])
            if root.right:
                edges[root.val].append([root.right.val, 'R'])
                edges[root.right.val].append([root.val, 'U'])
            traverse(root.left)
            traverse(root.right)

        def dfs(start, dest, t):
            nonlocal ans
            if start in visited:
                return
            if start == dest:
                if ans is None or len(ans) > len(t):
                    ans = ''.join(t)
                return
            visited.add(start)
            for d, k in edges[start]:
                t.append(k)
                dfs(d, dest, t)
                t.pop()

        traverse(root)
        dfs(startValue, destValue, [])
        return ans

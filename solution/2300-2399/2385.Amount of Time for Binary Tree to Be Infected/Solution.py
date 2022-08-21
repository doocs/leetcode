# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        def dfs(root):
            if root is None:
                return
            if root.left:
                g[root.val].append(root.left.val)
                g[root.left.val].append(root.val)
            if root.right:
                g[root.val].append(root.right.val)
                g[root.right.val].append(root.val)
            dfs(root.left)
            dfs(root.right)

        g = defaultdict(list)
        dfs(root)
        vis = set()
        q = deque([start])
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i = q.popleft()
                vis.add(i)
                for j in g[i]:
                    if j not in vis:
                        q.append(j)
        return ans

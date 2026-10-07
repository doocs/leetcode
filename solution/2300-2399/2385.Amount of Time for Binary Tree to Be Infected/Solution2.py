# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        g = defaultdict(list)
        stk = [(root, None)]
        while stk:
            node, fa = stk.pop()
            if node is None:
                continue
            if fa:
                g[node.val].append(fa.val)
                g[fa.val].append(node.val)
            stk.append((node.right, node))
            stk.append((node.left, node))

        dist = {}
        walk = [(start, -1, 0)]
        while walk:
            node, fa, state = walk.pop()
            nxts = g[node]
            if state == 0:
                walk.append((node, fa, 1))
                for nxt in reversed(nxts):
                    if nxt != fa:
                        walk.append((nxt, node, 0))
                continue
            best = 0
            for nxt in nxts:
                if nxt != fa:
                    best = max(best, 1 + dist[nxt])
            dist[node] = best
        return dist[start]

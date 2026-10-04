"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def diameter(self, root: 'Node') -> int:
        """
        :type root: 'Node'
        :rtype: int
        """
        if root is None:
            return 0
        g = defaultdict(list)
        seen = {root}
        stk = [root]
        while stk:
            u = stk.pop()
            for child in u.children:
                if child is None or child in seen:
                    continue
                seen.add(child)
                g[u].append(child)
                g[child].append(u)
                stk.append(child)

        def farthest(start):
            vis = {start}
            walk = [(start, 0)]
            best, node = 0, start
            while walk:
                u, t = walk.pop()
                if t > best:
                    best, node = t, u
                for v in g[u]:
                    if v not in vis:
                        vis.add(v)
                        walk.append((v, t + 1))
            return best, node

        _, nxt = farthest(root)
        ans, _ = farthest(nxt)
        return ans

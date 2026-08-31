"""
# Definition for a Node.
class Node(object):
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""


class Codec:
    def serialize(self, root: 'Node') -> str:
        if root is None:
            return ''
        ans = [str(root.val)]
        q = deque([root])
        while q:
            node = q.popleft()
            for child in node.children or []:
                ans.append(str(child.val))
                q.append(child)
            ans.append('#')
        return ','.join(ans)

    def deserialize(self, data: str) -> 'Node':
        if not data:
            return None
        vals = data.split(',')
        root = Node(int(vals[0]), [])
        q = deque([root])
        i = 1
        while q:
            node = q.popleft()
            while vals[i] != '#':
                child = Node(int(vals[i]), [])
                node.children.append(child)
                q.append(child)
                i += 1
            i += 1
        return root


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))

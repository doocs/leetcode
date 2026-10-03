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
        def lca(root: Optional[TreeNode], p: int, q: int):
            ret = {}
            stk = [(root, 0)]
            while stk:
                node, state = stk.pop()
                if state == 0:
                    if node is None:
                        continue
                    if node.val in (p, q):
                        ret[id(node)] = node
                        continue
                    stk.append((node, 1))
                    stk.append((node.right, 0))
                    stk.append((node.left, 0))
                else:
                    left = ret.get(id(node.left)) if node.left is not None else None
                    right = ret.get(id(node.right)) if node.right is not None else None
                    if left and right:
                        ret[id(node)] = node
                    else:
                        ret[id(node)] = left or right
            return ret.get(id(root))

        def dfs(start: Optional[TreeNode], x: int, path: List[str]) -> bool:
            stk = [(start, 0)]
            while stk:
                node, state = stk.pop()
                if state == 0:
                    if node is None:
                        continue
                    if node.val == x:
                        return True
                    path.append('L')
                    stk.append((node, 1))
                    stk.append((node.left, 0))
                elif state == 1:
                    path[-1] = 'R'
                    stk.append((node, 2))
                    stk.append((node.right, 0))
                else:
                    path.pop()
            return False

        node = lca(root, startValue, destValue)
        path_to_start: List[str] = []
        path_to_dest: List[str] = []
        dfs(node, startValue, path_to_start)
        dfs(node, destValue, path_to_dest)
        return 'U' * len(path_to_start) + ''.join(path_to_dest)

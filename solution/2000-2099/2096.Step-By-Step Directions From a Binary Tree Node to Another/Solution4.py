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

        path_to_start: List[str] = []
        path_to_dest: List[str] = []
        dfs(root, startValue, path_to_start)
        dfs(root, destValue, path_to_dest)
        i = 0
        while (
            i < len(path_to_start)
            and i < len(path_to_dest)
            and path_to_start[i] == path_to_dest[i]
        ):
            i += 1
        return 'U' * (len(path_to_start) - i) + ''.join(path_to_dest[i:])

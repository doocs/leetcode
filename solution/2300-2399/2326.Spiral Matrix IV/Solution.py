# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def spiralMatrix(self, m: int, n: int, head: Optional[ListNode]) -> List[List[int]]:
        ans = [[-1] * n for _ in range(m)]
        i = j = p = 0
        dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        while 1:
            ans[i][j] = head.val
            head = head.next
            if not head:
                break
            while 1:
                x, y = i + dirs[p][0], j + dirs[p][1]
                if x < 0 or y < 0 or x >= m or y >= n or ~ans[x][y]:
                    p = (p + 1) % 4
                else:
                    i, j = x, y
                    break
        return ans

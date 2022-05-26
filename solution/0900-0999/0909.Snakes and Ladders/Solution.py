class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        def get(x):
            i, j = (x - 1) // n, (x - 1) % n
            if i & 1:
                j = n - 1 - j
            return n - 1 - i, j

        n = len(board)
        q = deque([1])
        vis = {1}
        ans = 0
        while q:
            for _ in range(len(q)):
                curr = q.popleft()
                if curr == n * n:
                    return ans
                for next in range(curr + 1, min(curr + 7, n * n + 1)):
                    i, j = get(next)
                    if board[i][j] != -1:
                        next = board[i][j]
                    if next not in vis:
                        q.append(next)
                        vis.add(next)
            ans += 1
        return -1

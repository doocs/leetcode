rook_dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]
bishop_dirs = [(1, 1), (1, -1), (-1, 1), (-1, -1)]
queue_dirs = rook_dirs + bishop_dirs


def get_dirs(piece: str) -> List[Tuple[int, int]]:
    match piece[0]:
        case "r":
            return rook_dirs
        case "b":
            return bishop_dirs
        case _:
            return queue_dirs


class Solution:
    def countCombinations(self, pieces: List[str], positions: List[List[int]]) -> int:
        def check_stop(i: int, x: int, y: int, t: int) -> bool:
            return all(dist[j][x][y] < t for j in range(i))

        def check_pass(i: int, x: int, y: int, t: int) -> bool:
            for j in range(i):
                if dist[j][x][y] == t:
                    return False
                if end[j][0] == x and end[j][1] == y and end[j][2] <= t:
                    return False
            return True

        def dfs(i: int) -> None:
            if i >= n:
                nonlocal ans
                ans += 1
                return
            x, y = positions[i]
            dist[i][:] = [[-1] * m for _ in range(m)]
            dist[i][x][y] = 0
            end[i] = (x, y, 0)
            if check_stop(i, x, y, 0):
                dfs(i + 1)
            dirs = get_dirs(pieces[i])
            for dx, dy in dirs:
                dist[i][:] = [[-1] * m for _ in range(m)]
                dist[i][x][y] = 0
                nx, ny, nt = x + dx, y + dy, 1
                while 1 <= nx < m and 1 <= ny < m and check_pass(i, nx, ny, nt):
                    dist[i][nx][ny] = nt
                    end[i] = (nx, ny, nt)
                    if check_stop(i, nx, ny, nt):
                        dfs(i + 1)
                    nx += dx
                    ny += dy
                    nt += 1

        n = len(pieces)
        m = 9
        dist = [[[-1] * m for _ in range(m)] for _ in range(n)]
        end = [(0, 0, 0) for _ in range(n)]
        ans = 0
        dfs(0)
        return ans

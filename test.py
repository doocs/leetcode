class Solution:
    def isPathCrossing(self, path: str) -> bool:
        x = y = 0
        visited = set()
        visited.add(f'{x}.{y}')
        for c in path:
            if c == 'N':
                y += 1
            elif c == 'S':
                y -= 1
            elif c == 'E':
                x += 1
            else:
                x -= 1
            pos = f'{x}.{y}'
            if pos in visited:
                return True
            visited.add(pos)
        return False


if __name__ == '__main__':
    print(Solution().isPathCrossing("NESWW"))
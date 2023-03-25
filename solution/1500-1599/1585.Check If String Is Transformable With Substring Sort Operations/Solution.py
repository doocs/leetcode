class Solution:
    def isTransformable(self, s: str, t: str) -> bool:
        pos = defaultdict(deque)
        for i, c in enumerate(s):
            pos[int(c)].append(i)
        for c in t:
            x = int(c)
            if not pos[x] or any(pos[i] and pos[i][0] < pos[x][0] for i in range(x)):
                return False
            pos[x].popleft()
        return True

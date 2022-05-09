class Solution:
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        s = set(forbidden)
        q = deque([(0, 0)])
        vis = {(0, 1), (0, -1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, dir = q.popleft()
                if i == x:
                    return ans
                nxt = [(i + a, 1)]
                if dir != -1:
                    nxt.append((i - b, -1))
                for j, dir in nxt:
                    if 0 <= j <= 6000 and j not in s and (j, dir) not in vis:
                        vis.add((j, dir))
                        q.append((j, dir))
            ans += 1
        return -1

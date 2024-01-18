class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        q = deque([s])
        vis = {s}
        ans = s
        while q:
            s = q.popleft()
            if ans > s:
                ans = s
            t1 = ''.join(
                [str((int(c) + a) % 10) if i & 1 else c for i, c in enumerate(s)]
            )
            t2 = s[-b:] + s[:-b]
            for t in (t1, t2):
                if t not in vis:
                    vis.add(t)
                    q.append(t)
        return ans

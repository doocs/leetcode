class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        q = deque([s])
        vis = {s}
        ans = s
        while q:
            s = q.popleft()
            if s < ans:
                ans = s
            nxt1 = ''.join(
                [str((int(c) + a) % 10) if i & 1 else c for i, c in enumerate(s)]
            )
            nxt2 = s[-b:] + s[:-b]
            for nxt in (nxt1, nxt2):
                if nxt not in vis:
                    vis.add(nxt)
                    q.append(nxt)
        return ans

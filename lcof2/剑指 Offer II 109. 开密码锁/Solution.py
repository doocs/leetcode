class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        s = set(deadends)
        if target in s or '0000' in s:
            return -1
        if target == '0000':
            return 0

        def prev(c):
            return '9' if c == '0' else str(int(c) - 1)

        def next(c):
            return '0' if c == '9' else str(int(c) + 1)

        def get(t):
            res = []
            t = list(t)
            for i in range(4):
                c = t[i]
                t[i] = prev(c)
                res.append(''.join(t))
                t[i] = next(c)
                res.append(''.join(t))
                t[i] = c
            return res

        visited = set()
        q = deque([('0000', 0)])
        while q:
            status, step = q.popleft()
            for t in get(status):
                if t in visited or t in s:
                    continue
                if t == target:
                    return step + 1
                q.append((t, step + 1))
                visited.add(t)
        return -1

class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        def next(s):
            res = []
            s = list(s)
            for i in range(4):
                c = s[i]
                s[i] = '9' if c == '0' else str(int(c) - 1)
                res.append(''.join(s))
                s[i] = '0' if c == '9' else str(int(c) + 1)
                res.append(''.join(s))
                s[i] = c
            return res

        def f(s):
            ans = 0
            for i in range(4):
                a = ord(s[i]) - ord('0')
                b = ord(target[i]) - ord('0')
                if a > b:
                    a, b = b, a
                ans += min(b - a, a + 10 - b)
            return ans

        if target == '0000':
            return 0
        s = set(deadends)
        if '0000' in s:
            return -1
        start = '0000'
        q = [(f(start), start)]
        dist = {start: 0}
        while q:
            _, state = heappop(q)
            if state == target:
                return dist[state]
            for t in next(state):
                if t in s:
                    continue
                if t not in dist or dist[t] > dist[state] + 1:
                    dist[t] = dist[state] + 1
                    heappush(q, (dist[t] + f(t), t))
        return -1

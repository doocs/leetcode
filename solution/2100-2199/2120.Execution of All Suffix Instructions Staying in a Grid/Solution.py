class Solution:
    def executeInstructions(self, n: int, startPos: List[int], s: str) -> List[int]:
        ans = []
        m = len(s)
        mp = {"L": [0, -1], "R": [0, 1], "U": [-1, 0], "D": [1, 0]}
        for i in range(m):
            x, y = startPos
            t = 0
            for j in range(i, m):
                a, b = mp[s[j]]
                if 0 <= x + a < n and 0 <= y + b < n:
                    x, y, t = x + a, y + b, t + 1
                else:
                    break
            ans.append(t)
        return ans

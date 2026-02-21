pos = {}
keys = ['qwertyuiop', 'asdfghjkl', 'zxcvbnm']
for i, row in enumerate(keys):
    for j, key in enumerate(row):
        pos[key] = (i, j)


class Solution:
    def totalDistance(self, s: str) -> int:
        pre = 'a'
        ans = 0
        for cur in s:
            x1, y1 = pos[pre]
            x2, y2 = pos[cur]
            dist = abs(x1 - x2) + abs(y1 - y2)
            ans += dist
            pre = cur
        return ans

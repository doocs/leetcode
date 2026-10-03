class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        g = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            g[a].append(b)
            g[b].append(a)
        color = [0] * n
        for start in range(n):
            if color[start]:
                continue
            color[start] = 1
            stk = [start]
            while stk:
                i = stk.pop()
                for j in g[i]:
                    if color[j] == color[i]:
                        return False
                    if color[j] == 0:
                        color[j] = 3 - color[i]
                        stk.append(j)
        return True

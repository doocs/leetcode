class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        g = [[] for _ in range(nodes)]
        for i in range(1, nodes):
            g[parent[i]].append(i)
        sub = [(0, 0)] * nodes
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j in g[i]:
                    stk.append((j, 0))
            else:
                s, m = value[i], 1
                for j in g[i]:
                    t, c = sub[j]
                    s += t
                    m += c
                if s == 0:
                    m = 0
                sub[i] = (s, m)
        return sub[0][1]

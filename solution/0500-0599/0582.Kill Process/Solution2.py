class Solution:
    def killProcess(self, pid: List[int], ppid: List[int], kill: int) -> List[int]:
        g = defaultdict(list)
        for i, p in zip(pid, ppid):
            g[p].append(i)
        ans = []
        stk = [kill]
        while stk:
            i = stk.pop()
            ans.append(i)
            for j in reversed(g[i]):
                stk.append(j)
        return ans

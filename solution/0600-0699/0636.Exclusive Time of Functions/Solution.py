class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        ans = [0] * n
        stk = []
        curr = -1
        for log in logs:
            t = log.split(':')
            fid = int(t[0])
            ts = int(t[2])
            if t[1] == 'start':
                if stk:
                    ans[stk[-1]] += ts - curr
                stk.append(fid)
                curr = ts
            else:
                fid = stk.pop()
                ans[fid] += ts - curr + 1
                curr = ts + 1
        return ans

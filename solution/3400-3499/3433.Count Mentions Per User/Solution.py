class Solution:
    def countMentions(self, numberOfUsers: int, events: List[List[str]]) -> List[int]:
        events.sort(key=lambda e: (int(e[1]), e[0][2]))
        ans = [0] * numberOfUsers
        online_t = [0] * numberOfUsers
        lazy = 0
        for etype, ts, s in events:
            cur = int(ts)
            if etype[0] == "O":
                online_t[int(s)] = cur + 60
            elif s[0] == "A":
                lazy += 1
            elif s[0] == "H":
                for i, t in enumerate(online_t):
                    if t <= cur:
                        ans[i] += 1
            else:
                for a in s.split():
                    ans[int(a[2:])] += 1
        if lazy:
            for i in range(numberOfUsers):
                ans[i] += lazy
        return ans

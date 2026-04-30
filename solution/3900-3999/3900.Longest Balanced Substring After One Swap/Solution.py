class Solution:
    def longestBalanced(self, s: str) -> int:
        cnt0 = s.count("0")
        cnt1 = len(s) - cnt0
        pos = {0: [-1]}
        ans = pre = 0
        for i, c in enumerate(s):
            pre += 1 if c == "1" else -1
            pos.setdefault(pre, []).append(i)

            ans = max(ans, i - pos[pre][0])
            if pre - 2 in pos:
                p = pos[pre - 2]
                if (i - p[0] - 2) // 2 < cnt0:
                    ans = max(ans, i - p[0])
                elif len(p) > 1:
                    ans = max(ans, i - p[1])

            if pre + 2 in pos:
                p = pos[pre + 2]
                if (i - p[0] - 2) // 2 < cnt1:
                    ans = max(ans, i - p[0])
                elif len(p) > 1:
                    ans = max(ans, i - p[1])
        return ans

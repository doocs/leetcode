class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        t = []
        i, n = 0, len(s)
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            cnt = j - i
            cnt %= k
            if t and t[-1][0] == s[i]:
                t[-1][1] = (t[-1][1] + cnt) % k
                if t[-1][1] == 0:
                    t.pop()
            elif cnt:
                t.append([s[i], cnt])
            i = j
        ans = [c * v for c, v in t]
        return "".join(ans)

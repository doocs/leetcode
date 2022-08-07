class Solution:
    def minWindow(self, s: str, t: str) -> str:
        ans = ''
        m, n = len(s), len(t)
        if m < n:
            return ans
        need = Counter(t)
        window = Counter()
        i, cnt, mi = 0, 0, inf
        for j, c in enumerate(s):
            window[c] += 1
            if need[c] >= window[c]:
                cnt += 1
            while cnt == n:
                if j - i + 1 < mi:
                    mi = j - i + 1
                    ans = s[i : j + 1]
                c = s[i]
                if need[c] >= window[c]:
                    cnt -= 1
                window[c] -= 1
                i += 1
        return ans
